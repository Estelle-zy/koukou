package com.example.koukou.config;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.koukou.entity.LogInfo;
import com.example.koukou.service.LogInfoService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.net.InetAddress;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Author
 * @Date 2022/5/30 16:15
 * @Version 1.0
 * @Description 系统日志：切面处理类
 **/
@Aspect
@Component
public class LogInfoAspect {
    @Autowired
    private LogInfoService service;

    /**
     * 换行符
     */
    private static final String LINE_SEPARATOR = System.lineSeparator();
    private final static Logger log = LoggerFactory.getLogger(LogInfoAspect.class);

    /**
     * 以自定义 @LogInfoAnno 注解为切点
     */
    @Pointcut("@annotation(com.example.koukou.config.LogInfoAnno)")
    public void webLog() {
    }

    @Before("webLog()")
    public void doBefore(JoinPoint joinPoint) throws Exception {
        LogInfo logInfo = new LogInfo();
        // 进入方法前 调用开始
        System.out.println("切面开始：=============== Strat ===================");
        // 开始打印请求日志
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        //获取请求地址URL
        String servletPath = request.getServletPath();
        //ip地址
        String ipAddr = getIpAddr(request);
        // 获取 @LogInfoAnno 注解上的的描述信息
        List<String> listDesc = getAspectLogDescription(joinPoint);
        //描述信息
        String description = listDesc.get(0);
        //调用模块
        String businessType = listDesc.get(1);

        //参数信息
        StringBuilder builder = new StringBuilder();
        builder.append(description + ":" + joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());

        Object[] args = joinPoint.getArgs();
        Object[] arguments  = new Object[args.length];
        for (int i = 0; i < args.length; i++) {
            if (args[i] instanceof ServletRequest || args[i] instanceof ServletResponse || args[i] instanceof MultipartFile) {
                //ServletRequest不能序列化，从入参里排除，否则报异常：java.lang.IllegalStateException: It is illegal to call this method if the current request is not in asynchronous mode (i.e. isAsyncStarted() returns false)
                //ServletResponse不能序列化 从入参里排除，否则报异常：java.lang.IllegalStateException: getOutputStream() has already been called for this response
                continue;
            }
            arguments[i] = args[i];
        }
        String paramters = JSONObject.toJSONString(arguments);
        //调用时间
        SimpleDateFormat simpleDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        logInfo.setCallTime(simpleDate.format(new Date()));
        logInfo.setUrl(servletPath);
        logInfo.setIp(ipAddr);
        logInfo.setMsg(description);
        logInfo.setType(businessType);
        logInfo.setParam(paramters);

        service.add(logInfo);
    }

    /**
     * 在切点之后织入
     *
     * @throws Throwable
     */
    @After("webLog()")
    public void doAfter() throws Throwable {
        // 接口结束后换行，方便分割查看
        System.out.println("切面结束：=========== End ================");
    }

    /**
     * 获取切面注解的描述
     *
     * @param joinPoint 切点
     * @return 描述信息
     * @throws Exception
     */
    public List<String> getAspectLogDescription(JoinPoint joinPoint)
            throws Exception {
        String targetName = joinPoint.getTarget().getClass().getName();
        String methodName = joinPoint.getSignature().getName();
        Object[] arguments = joinPoint.getArgs();
        Class targetClass = Class.forName(targetName);
        Method[] methods = targetClass.getMethods();
        StringBuilder description = new StringBuilder("");
        StringBuilder businessType = new StringBuilder("");
        for (Method method : methods) {
            if (method.getName().equals(methodName)) {
                Class[] clazzs = method.getParameterTypes();
                if (clazzs.length == arguments.length) {
                    description.append(method.getAnnotation(LogInfoAnno.class).description());
                    businessType.append(method.getAnnotation(LogInfoAnno.class).businessType());
                    break;
                }
            }
        }
        List<String> list = new ArrayList<>();
        list.add(description.toString());
        list.add(businessType.toString());
        return list;
    }

    public final static String getIpAddr(HttpServletRequest request) {
        String ipAddress = null;
        try {
            ipAddress = request.getHeader("x-forwarded-for");
            if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
                ipAddress = request.getHeader("Proxy-Client-IP");
            }
            if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
                ipAddress = request.getHeader("WL-Proxy-Client-IP");
            }
            if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
                ipAddress = request.getRemoteAddr();
                if (ipAddress.equals("127.0.0.1")) {
                    // 根据网卡取本机配置的IP
                    InetAddress inet = null;
                    try {
                        inet = InetAddress.getLocalHost();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    ipAddress = inet.getHostAddress();
                }
            }
            // 对于通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割
            if (ipAddress != null && ipAddress.length() > 15) { // "***.***.***.***".length()
                // = 15
                if (ipAddress.indexOf(",") > 0) {
                    ipAddress = ipAddress.substring(0, ipAddress.indexOf(","));
                }
            }
        } catch (Exception e) {
            ipAddress = "";
        }
        return ipAddress;
    }

}


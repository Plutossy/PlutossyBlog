//package com.plutossy.aspect;
//
//import org.aspectj.lang.JoinPoint;
//import org.aspectj.lang.annotation.*;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.stereotype.Component;
//import org.springframework.web.context.request.RequestContextHolder;
//import org.springframework.web.context.request.ServletRequestAttributes;
//
//import javax.servlet.http.HttpServletRequest;
//import java.util.Arrays;
//
//@Aspect  //切面
//@Component
//public class LogAspect {
//    private final Logger logger= LoggerFactory.getLogger(this.getClass());
//
//    @Pointcut("execution(* com.plutossy.web.*.*(..))")  //拦截哪些
//    public void log(){}  //定义切面
//
//    //切面之前
//    @Before("log()")
//    public void doBefore(JoinPoint joinPoint){
//        //获取url、ip
//        ServletRequestAttributes attributes= (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
//        HttpServletRequest request=attributes.getRequest();
//        String url=request.getRequestURL().toString();
//        String ip=request.getRemoteAddr();
//
//        //通过切面joinPoint获取classMethod和args
//        String classMethod=joinPoint.getSignature().getDeclaringTypeName()+"."+joinPoint.getSignature().getName();
//        Object[] args=joinPoint.getArgs();
//
//        //构造对象
//        RequestLog requestLog=new RequestLog(url,ip,classMethod,args);
//        logger.info("Request : {}",requestLog);
//    }
//    @After("log()")
//    public void doAfter(){
////        logger.info("-----doAfter-----");
//    }
//
//    //方法返回后拦截
//    @AfterReturning(returning = "result",pointcut = "log()")
//    public void doAfterReturn(Object result){
//        logger.info("Result : {}",result);
//    }
//    private class RequestLog{
//        private String url;
//        private  String ip;
//        private  String ClassMethod;
//        private  Object[] args;
//
//        public RequestLog(String url, String ip, String classMethod, Object[] args) {
//            this.url = url; //请求地址
//            this.ip = ip;   //访问者ip
//            ClassMethod = classMethod;   //调用方法
//            this.args = args;  //请求参数
//        }
//
//        @Override
//        public String toString() {
//            return "{" +
//                    "url='" + url + '\'' +
//                    ", ip='" + ip + '\'' +
//                    ", ClassMethod='" + ClassMethod + '\'' +
//                    ", args=" + Arrays.toString(args) +
//                    '}';
//        }
//    }
//}

package cn.hncu.filter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import cn.hncu.user.domain.User;



public class AuthInterceptor extends HandlerInterceptorAdapter {
    
    
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("过滤器=======================");
        System.out.println(HandlerMethod.class);
        System.out.println(handler.getClass().isAssignableFrom(HandlerMethod.class));
        System.out.println(handler.getClass());
        if(handler instanceof HandlerMethod){
            AuthPassport authPassport = ((HandlerMethod) handler).getMethodAnnotation(AuthPassport.class);
            System.out.println("11111111111");
            //没有声明需要权限,或者声明不验证权限
                if(authPassport == null || authPassport.validate() == false)
                return true;
            else{ 
            	Object admin = request.getSession().getAttribute("sessionUser");
        		System.out.println("222222222222"+admin);
                //在这里实现自己的权限验证逻辑
                if(admin!=null)//如果验证成功返回true（这里直接写false来模拟验证失败的处理）
                    return true;
                else//如果验证失败
                {
                    //返回到登录界面
                	System.out.println("没有==================");
                    request.setAttribute("msg", "您还没有登录，不要瞎遛达！");
        			response.sendRedirect("/myShop/jsp/user/login.jsp");
                    return false;
                }       
            }
        }
        else{
        	System.out.println("通过");
        	return true;   
        }
        }

}

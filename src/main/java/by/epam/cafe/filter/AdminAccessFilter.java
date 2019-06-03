package by.epam.cafe.filter;

import by.epam.cafe.constant.GeneralConstant;
import by.epam.cafe.entity.UserEntity;
import by.epam.cafe.type.PageType;
import by.epam.cafe.validator.impl.UserValidatorImpl;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(filterName = "AdminAccessFilter", urlPatterns = "/pages/admin_panel/*",
        dispatcherTypes = {DispatcherType.REQUEST, DispatcherType.FORWARD})
public class AdminAccessFilter implements Filter {
    public void init(FilterConfig fConfig) throws ServletException {}

    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        UserValidatorImpl validator = new UserValidatorImpl();
        UserEntity user = (UserEntity) httpRequest.getSession().getAttribute(GeneralConstant.USER);

        if (!validator.isAdmin(user)) {
            httpResponse.sendRedirect(PageType.INDEX.getPage());
            return;
        }

        chain.doFilter(request, response);
    }

    public void destroy() {
    }
}

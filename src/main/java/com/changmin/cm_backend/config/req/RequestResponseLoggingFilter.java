package com.changmin.cm_backend.config.req;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Slf4j
@Component
public class RequestResponseLoggingFilter implements Filter {

  @Override
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
      throws IOException, ServletException {

    HttpServletRequest req = (HttpServletRequest) request;
    //    log.info(
    //        "-------------------- request [{}] parameter [{}] with method [{}]
    // --------------------",
    //        req.getRequestURI(),
    //        req.getQueryString(),
    //        req.getMethod());
    //        ContentCachingRequestWrapper wrapper = new ContentCachingRequestWrapper(req);
    chain.doFilter(req, response);
    //    log.info(
    //        "-------------------- request [{}] ended in [{}], return type [{}] with status code
    // [{}], body [{}] --------------------",
    //        req.getRequestURI(),
    //        Stopwatch.createStarted(),
    //        res.getContentType(),
    //        res.getStatus(),
    //        res.getStatus() >= 500 ? new String(wrapper.getContentAsByteArray()) : null);
  }
}

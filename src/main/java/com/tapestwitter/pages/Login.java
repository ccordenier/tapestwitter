package com.tapestwitter.pages;

import org.apache.tapestry5.EventConstants;
import org.apache.tapestry5.annotations.Import;
import org.apache.tapestry5.annotations.OnEvent;
import org.apache.tapestry5.ioc.annotations.Inject;

import com.tapestwitter.pages.home.Dashboard;
import com.tapestwitter.services.TapestwiterURLResolver;
import com.tapestwitter.services.security.TapestwitterSecurityContext;

@Import(stylesheet = "context:/forms/forms.css")
public class Login
{
    @Inject
    private TapestwitterSecurityContext securityContext;

    @Inject
    private TapestwiterURLResolver urlResolver;

    private boolean error;

    @OnEvent(value = EventConstants.ACTIVATE)
    public Object checkSecurityCtx()
    {
        if (securityContext.isLoggedIn()) { return Dashboard.class; }
        return null;
    }

    @OnEvent(value = EventConstants.ACTIVATE)
    public void checkError(String extra)
    {
        if (extra.equals("error"))
        {
            error = true;
        }
    }

    public String getLoginUrl()
    {
        return urlResolver.getLoginUrl();
    }

    public boolean isError()
    {
        return error;
    }

}

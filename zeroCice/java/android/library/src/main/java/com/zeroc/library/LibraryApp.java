//
// Copyright (c) ZeroC, Inc. All rights reserved.
//

package com.zeroc.library;

import com.zeroc.library.controller.LoginController;
import com.zeroc.library.controller.SessionController;
import com.zeroc.library.controller.LoginController.Listener;

import android.app.Application;

public class LibraryApp extends Application
{
    private LoginController _loginController;
    private SessionController _sessionController;

    @Override
    public void onTerminate()
    {
        super.onTerminate();

        if(_loginController != null)
        {
            _loginController.destroy();
            _loginController = null;
        }

        if(_sessionController != null)
        {
            _sessionController.destroy();
            _sessionController = null;
        }
    }

    public void loggedIn(SessionController sessionController)
    {
        assert _sessionController == null && _loginController != null;
        _sessionController = sessionController;

        _loginController.destroy();
        _loginController = null;
    }

    public void logout()
    {
        if(_sessionController != null)
        {
            _sessionController.destroy();
            _sessionController = null;
        }
    }

    public LoginController login(String username, String password, Listener listener)
    {
        assert _loginController == null && _sessionController == null;
        _loginController = new LoginController(getResources(), username, password, listener);
        return _loginController;
    }

    public void loginFailure()
    {
        if(_loginController != null)
        {
            _loginController.destroy();
            _loginController = null;
        }
    }

    public SessionController getSessionController()
    {
        return _sessionController;
    }

    public LoginController getLoginController()
    {
        return _loginController;
    }
}

//
// Copyright (c) ZeroC, Inc. All rights reserved.
//

import com.zeroc.demos.IceGrid.icebox.Demo.*;

public class HelloI implements Hello
{
    HelloI(String serviceName)
    {
        _serviceName = serviceName;
    }

    @Override
    public void sayHello(com.zeroc.Ice.Current current)
    {
        java.util.Map<String,String> env =  System.getenv();
        String lang = env.containsKey("LANG") ? env.get("LANG") : "en";
        String greeting = "Hello, ";
        if(lang.equals("fr"))
        {
            greeting = "Bonjour, ";
        }
        else if(lang.equals("de"))
        {
            greeting = "Hallo, ";
        }
        else if(lang.equals("es"))
        {
            greeting = "Hola, ";
        }
        else if(lang.equals("it"))
        {
            greeting = "Ciao, ";
        }
        System.out.println(greeting + _serviceName);
    }

    private String _serviceName;
}

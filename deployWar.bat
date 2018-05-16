echo off
del C:\Users\Admin\external-application\wildfly-10.1.0.Final\standalone\deployments\CinemaApp.war
del C:\Users\Admin\external-application\wildfly-10.1.0.Final\standalone\deployments\CinemaApp.war.deployed

xcopy /s C:\Users\Admin\eclipse-workspace\CinemaApp\target\CinemaApp.war C:\Users\Admin\external-application\wildfly-10.1.0.Final\standalone\deployments

call C:\Users\Admin\external-application\wildfly-10.1.0.Final\bin\standalone.bat
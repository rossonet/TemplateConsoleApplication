[![Gitpod ready-to-code](https://img.shields.io/badge/Gitpod-ready--to--code-blue?logo=gitpod)](https://gitpod.io/#https://github.com/rossonet/TemplateEdgeAgentAr4k)
[![Codacy Badge](https://app.codacy.com/project/badge/Grade/1fe4b5ad3b92495ca581b02926af62a8)](https://www.codacy.com/gh/rossonet/TemplateEdgeAgentAr4k/dashboard?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=rossonet/TemplateEdgeAgentAr4k&amp;utm_campaign=Badge_Grade)
[![Test on master branch with Maven](https://github.com/rossonet/TemplateEdgeAgentAr4k/actions/workflows/test-on-master-with-maven.yml/badge.svg)](https://github.com/rossonet/TemplateEdgeAgentAr4k/actions/workflows/test-on-master-with-maven.yml)
[![Test on master branch with Gradle](https://github.com/rossonet/TemplateEdgeAgentAr4k/actions/workflows/test-on-master-with-gradle.yml/badge.svg)](https://github.com/rossonet/TemplateEdgeAgentAr4k/actions/workflows/test-on-master-with-gradle.yml)

# Template Edge app Rossonet

## Reference

[Library Documentation](https://www.rossonet.net/dati/edge-docs/doc-site/)

[Tech blog](https://app.rossonet.net/?page_id=13)

## Run as a service

### Install as local init.d service

The script supports the following features:

- Starts the services as the user that owns the jar file

- Tracks the application’s PID by using /var/run/ar4kAgent/ar4kAgent.pid

- Writes console logs to /var/log/ar4kAgent.log

Assuming that you have a Ar4k Agent application installed in /var/ar4kAgent, to install it as an init.d service, create a symlink, as follows:

```
$ sudo ln -s /var/ar4kAgent/ar4kAgent.jar /etc/init.d/ar4kAgent
```

Once installed, you can start and stop the service in the usual way. For example, on a Debian-based system, you could start it with the following command:

```
$ service ar4kAgent start
```

### Install as Systemd service

Systemd is the successor of the System V init system and is now being used by many modern Linux distributions. Although you can continue to use init.d scripts with systemd, it is also possible to launch Ar4kAgent by using systemd ‘service’ scripts.

Assuming that you have a Ar4kAgent installed in /var/ar4kAgent, to install a Spring Boot application as a systemd service, create a script named ar4kAgent.service and place it in /etc/systemd/system directory. The following script offers an example:

```
[Unit]
Description=Ar4k Agent 
After=syslog.target

[Service]
Type=simple
User=root
#ExecStartPre=/opt/pre-start.sh
#ExecStopPost=/opt/post-stop.sh
ExecStart=/var/ar4kAgent/ar4kAgent.jar --spring.shell.interactive.enabled=false
PIDFile=/tmp/ar4k-agent.pid
RemainAfterExit=no
Restart=on-failure
RestartSec=5s

[Install]
WantedBy=multi-user.target
```

Note that, unlike when running as an init.d service, the user that runs the application, the PID file, and the console log file are managed by systemd itself and therefore must be configured by using appropriate fields in the ‘service’ script. Consult the service unit configuration man page for more details.

To flag the application to start automatically on system boot, use the following command:

```
$ systemctl enable ar4kAgent.service
```

Refer to man systemctl for more details.

### Install as Windows service

The windows distribution uses [winsw](https://github.com/kohsuke/winsw).

Warning

You need to install the .NET framework on the target machine first.

To install the sample application as a service, extract the distribution somewhere and open a shell with administrative rights in that directory. Then invoke the following command

```
$ ar4kAgent install
```

Once the service has been installed, you can start it the usual way, that is:

```
$ net start ar4kAgent
```

The logs are available in the logs directory of the distribution.

### Docker build

```
git clone https://github.com/rossonet/TemplateEdgeAgentAr4k.git
cd TemplateEdgeAgentAr4k
docker build --rm -t ar4k-agent:latest .

[...]

docker run -ti --rm ar4k-agent:latest
```


![alt text](https://www.rossonet.net/wp-content/uploads/2015/01/logoRossonet4.png "Rossonet")

[https://www.rossonet.net](https://www.rossonet.net)


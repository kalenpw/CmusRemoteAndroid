# CmusRemoteAndroid

An app to control <a href="https://cmus.github.io/">Cmus</a> music player from your Android phone.

CmusRemoteAndroid uses JSCH and controls cmus through cmus-remote on your computer thus there is no need to have cmus listen on a newtwork socket, however SSH must be setup on the host computer.

Note: currently your password will be stored in a plain text file that only root has access too. The only way to avoid this is to require user to enter password frequently.

![Screenshot](http://i.imgur.com/E2Dfop9.png)

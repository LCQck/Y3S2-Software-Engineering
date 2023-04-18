前言：本记事本旨在收录实际运用Git(傻逼东西)来完成代码版本管理中
可能存在的真的会要命的问题，欢迎大家后续添加




Git命令解决不想更新提交本地代码，只想拉取服务器最新代码
1. git stash
2. git pull
3. git stash pop

Git在设置了git remote add origin “仓库的那个URL”后无法追踪到main分支
1. git branch --set-upstream-to=origin/main main 用来设置跟踪
2. 如果成功了那就没事了，但是如果报了下面这个，显示不存在origin/main这一分支
hint: run "git fetch" to retrieve it.
hint: will track its remote counterpart, you may want to use
那么你需要启用 git fetch
3. 再次 git branch --set-upstream-to=origin/main main应该就能成功了


Git 尝试git fetch来获取远程分支后报错error: RPC failed； curl 28 OpenSSL 
SSL_read: Connection was reset, errno 10054 fatal: expected
1. git config --global http.sslVerify "false"
2. 再次尝试git fetch，如果报了
warning: ----------------- SECURITY WARNING ----------------
warning: ----------------- SECURITY WARNING ----------------
warning: ---------------------------------------------------
warning: HTTPS connections may not be secure. See https://aka.ms/gcm/tlsverify for more information.
那么恭喜你这个问题解决了，皆大欢喜
3. 接下来你会看到这个
From https://github.com/LeonardCong/Pizza-Ordering-System
 * [new branch]      main       -> origin/main
 * [new branch]      master     -> origin/master
那么代表着远程分支成功被你拉取，你就可以准备正常更新了
4. 最后！！！别忘了git config --global http.sslVerify "true" 来把刚修改的安全设置改回去

Git更新后报错refusing to merge unrelated histories，即无法合并
1. git pull origin main（或者master，看你本地在master还是在main上修改此处） --allow-unrelated-histories 
之后你应该会看到终端中出现下面这个
 * branch            main       -> FETCH_HEAD
然后如果有类似的这些
Auto-merging pom.xml
CONFLICT (add/add): Merge conflict in pom.xml
就代表你可以正常更新了，
如果没有并且你的项目下面多出了远程仓库的所属分支中别人更新的东西，也代表一切正常了 :)

Git不覆盖远程仓库完成更新
1. git push -u origin main（尚未经过测试，但是有一点，绝对不要在这个命令行结尾加上 -f ！
                                            不然所有的远程仓库的就会被你上传的本地文件完全覆盖 :(  ）
谨慎使用该命令行！！！




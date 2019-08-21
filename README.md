# ESUN-Summer-Project

## 計劃簡介

在分行內的存匯取款功能目前尚由紙本格式為主，為因應數位化以及響應環保，本系統將以去除紙張為目標設計出符合使用者特性的網頁，供顧客、行員及主管使用。

## 系統架構

     前端: Vue.js
     後端: Spring boot
     資料庫: SQL server

## 開發環境

    Eclipse 4.12.0
    Visual Studio Code 1.37.1
    (此系統使用到的開發軟體皆可在各平台使用)   

### 資料庫環境建置

[Docker](https://zh.wikipedia.org/wiki/Docker) 是一種軟體平台，可使用容器快速地建立、測試和部署應用程式。
，他的好處是輕量化，且可以跨平台開發。

MSSQL 在 Docker 上的 image 安裝可以參考此篇[官方文件](https://docs.microsoft.com/zh-tw/sql/linux/quickstart-install-connect-docker?view=sql-server-2017&pivots=cs1-bash)，照著上面的指令操作即可建構完成。

附上我們使用的 docker hub 可供下載：
```
$ docker pull qscefb5566/esp-sqlserver
```
**Docker 在使用資料庫時會發現無法插入中文資料，因此在建構資料庫時必須輸入以下指令：**
```
1> CREATE DATABASE your_db_name
1> COLLATE Chinese_PRC_CI_AS; 
1> GO
```
做完以上步驟後即可在終端機中使用 SQL 指令操控資料庫，但如果你用不習慣 command line ，想要使用圖形介面這裡也介紹一個 Microsoft 推出的 [Azure Data Studio](https://docs.microsoft.com/zh-tw/sql/azure-data-studio/download?view=sql-server-2017)，下載安裝好後只需要連線本地端的 SQL Server 帳密，並選擇資料庫，就可以使用像 MS SQL 那樣的圖形化介面，還有提供 notebook 的方式可以進行 Query，非常方便。
![](https://i.imgur.com/PDBmD8g.png)



### 後端環境建置

存取資料庫資料時我們需要設計屬於我們自己的 api 供前端使用，這也是後端主要負責的事情。這裡以 Java 撰寫，並使用 [Spring boot](https://spring.io/projects/spring-boot) 為框架開發。
傳統的 Spring 開發中需要使用大量的 XML 配置才能使 Spring 框架運行起來，而 Spring boot 主要是為了簡化應用的搭建及開發過程。

在開發之前需要將環境搭建好，需要以下：（公司電腦安裝環境非常麻煩，許多套件或工具必須手動下載後安裝，此部分就不詳細贅述，因為我們也沒裝好XD）
* [Eclipse](https://www.eclipse.org/downloads/)
下載後安裝完即可
* [Java SE Development Kit 8](https://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)
    * Windows 系統：在下載頁面點擊 windows 版本（x86 or x64）。
    下載後點擊安裝，基本上一直點下一步即可
    ![](https://i.imgur.com/Hj7zXUa.png)
    
        裝完後若你的電腦沒有 JRE ，安裝包會自動導引你安裝，一樣一直下一步就好。
    ![](https://i.imgur.com/JFtLop7.png)
    
        安裝好之後設定環境變數，按下 cmd+R 輸入 sysdm.cpl
    ![](https://i.imgur.com/JN9vCAi.png)
    
        1. 按下進階>環境變數>新增環境變數
        變數名稱：JAVA_HOME
        路徑：C:\Program Files\Java\jdk（你所下載的jdk版本）
        2. 按下確定完成新增
        3. 編輯 PATH 變數 
        4. 點選新增輸入「%JAVA_HOME%\bin」按下確定
        5. 儲存離開即設定完成
        6. 在 cmd 內輸入 java -version 若看到以下代表安裝成功
![](https://i.imgur.com/ibp2i55.png)



    * macOS: mac 安裝較為簡易
        1. 下載後安裝（一直下一步）
        2. 開啟 terminal 
        3. 輸入 vi ~/.bash_profile 或 vi ~/.profile 
        4. 加入這行 export JAVA_HOME=$(/usr/libexec/java_home) 後儲存離開
        5. 輸入 source ~/.bash_profile 或 source ~/.profile 即設定完成
        6. 在 terminal 中輸入 java -version 若看到以下代表安裝成功
![](https://i.imgur.com/2rTzeId.png)

    


* [Wildfly](https://wildfly.org/downloads/) 
    ![](https://i.imgur.com/vBN5D3n.png)
    1. 點擊下載後將檔案解壓縮放到你想要的路徑
    2. 打開 Eclipse>Help>market place 搜尋 jboss tool
![](https://i.imgur.com/6QbkhDL.png)
    3. 打開 preference>server>Runtime environment>add wildfly(下載的版本)
    ![](https://i.imgur.com/rvluSJd.png)
    Finish！
    4. 測試看看有無安裝成功：File>new>other 新增 wildfly server
![](https://i.imgur.com/dpxqwWF.png)
    5. 在 server tab 底下會出現如下，點擊右方的執行鍵
![](https://i.imgur.com/tBk13u9.png)
    6. run 成功打開瀏覽器輸入: localhost:8080 ，出現以下畫面代表安裝成功
![](https://i.imgur.com/t3JbxaE.png)

* Spring boot tool 安裝：
直接打開 Eclipse 的 market place 搜尋 spring boot 安裝即可
![](https://i.imgur.com/S3v0gAi.png)

    如何新增專案：
    1. 點擊 command(ctrl)+N 找到 spring starter project，或者是使用官方網站提供的 [spring initializr](https://start.spring.io/) 下載並匯入。
![](https://i.imgur.com/Cgd4V0K.png)

    2. 命名設定
![](https://i.imgur.com/YxVucnZ.png)

    3. 將需要的套件加入專案，點擊 finish 完成專案建置，且會自動生成 XML。
![](https://i.imgur.com/Ghez0Cu.png)

    4. 將新增的專案新增到 server 上：右鍵點擊 wildfly server 選擇 add and remove，將專案加進 Configured，點擊 finish 即可。
![](https://i.imgur.com/iZS3T42.png)

    5. 再 run 一次 wildfly server 試試看有沒有部署成功。
![](https://i.imgur.com/lBpmOFR.png)




到目前為止後端的開發環境已經安裝好了～

### 前端環境建置
Vue.js 是一套構建用戶界面的框架。其核心只關注視圖層，它不僅易於上手，還便於與第三方套件或既有專案整合。 Vue.js 關注的是網站 UI 的操作和資料關聯，Vue.js 和 React 相同採用了 Virtual DOM 的處理機制，讓 DOM 的變化更快速，也擁有和 AngularJS 相同的宣告式語法設計，讓開發可以更加直覺。

而使用 Vue 需要用到 Node.js、npm 等等，這裡介紹一個套件叫 nvm (Node.js Version Manager)，顧名思義就是讓 Node 的版本方便控管，可以依需求在不同版本間切換。

安裝方式一樣有分 Windows 跟 macOS：
* Windows：下載最新版本[安裝檔](https://github.com/coreybutler/nvm-windows/releases)，基本上也是一直下一步到底，安裝好後就可以在 cmd 中使用 nvm。

* macOS：使用 homebrew 安裝
    ```
    $ brew install nvm
    ```
    接下來要把 nvm 加入 shell 中，就可以在 terminal 中直接使用。
    ```
    $ echo "source $(brew --prefix nvm)/nvm.sh" >> .bash_profile
    $ . ~/.bash_profile
    ```

安裝好後可以在 cmd 或 terminal 中查看有哪些版本可用
```
$ nvm list available
```
安裝特定版本
```
$ nvm install <version>
```
也可以直接安裝最新版本或者最穩定版本
```
$ nvm install latest

or

$ nvm install stable
```
查看安裝版本
```
$ nvm ls
```
使用特定版本
```
$ nvm use <version>
```

安裝 Node.js 後就會一併擁有 Node Package Manager(npm) 程式。查看 npm 版本：
```
$ npm -v
```
前端環境建置完畢。

## 運行專案

這裡演示如何在同一台電腦同時開啟前後端及資料庫並運行。
1. 下載專案檔案：
```
$ git clone https://github.com/mrwenwei/ESUN-Summer-Project
```
2. pull 我們製作好的 image 檔在 docker 上使用，並執行資料庫（尚未完成）
3. 開啟後端 server: 
    * 在 Eclipse 中匯入資料夾中的「esp-back-end」資料夾![](https://i.imgur.com/S4zW3bF.png =300x)
    * 對原本建立好的 wildfly server 點右鍵>add and remove，將 esp-back-end 加入 server，執行後即完成後端部署
![](https://i.imgur.com/sU8nS6Q.png)
4. 開啟前端 server:
```
$ cd ESUN-Summer-Project/esp-front-end

$ npm install
```
開發模式：開啟 [localhost:8081](localhost:8081)(預設)
```
$ npm run dev 
```
打包
```
$ npm run build 
```
打包後會出現 dist 的資料夾，內部的文件直接放到伺服器上即可。

###### tags: `Spring boot` `Vue.js` `MSSQL` `Documentation`

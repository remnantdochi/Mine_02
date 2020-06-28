# Minecraft plugin making

## before start...
정말 정말 아무것도 모르는 나를 위한 기록 정리
### 서버 만들기  
일단 spigot 사용할거임  
https://www.spigotmc.org/ 접속  
Downloads 탭 클릭  
Buildtools.jar 다운로드  
다운로드할 위치는 서버 관련 자료 많이 생길테니까 빈 파일 따로 만들기  
그 곳에서 shift+우클릭 >> 여기에 power shell 창 열기  
`java -jar BuildTools.jar --rev <버전>` 또는 `java -jar BuildTools.jar`  
start.bat 파일 만들기  
아래와 같이 입력
```
@echo off  
java -Xms1G -Xmx1G -XX:+UseConcMarkSweepGC -jar <스피곳 파일 명>  
pause  
```  
start.bat 더블 클릭해서 실행 멈추면 eula에서 false >> true로 바꾸기  
plugins 폴더에 만든 플러그인 집어넣기 //BuildTool로 풀린 그 친구 ㅇㅇ
마인크래프트 실행하기  
멀티플레이에서 서버주소 `localhost`입력  
### 본격 서버 설정 건들기
서버 이미지 설정 : https://aochfl.tistory.com/249  
서버 op 주기 : ops.json  
`[
  {
    "uuid": "",
    "name": "",
    "level": 4,
    "bypassesPlayerLimit": false
  }
]`  
uuid 찾기 : https://mcuuid.net/?q=ChiD0  
spigo.yml에서 item 합쳐지는 거리 제거함  
https://www.spigotmc.org/threads/stop-dropped-items-from-stacking.120166/
### plugin 만들기
`public class Main extends JavaPlugin`  
java 프로젝트에서 우클릭> Build Path > Configure Build Path > Libraries > spigot 추가!  
plugin.yml필요  
참고 : http://blog.naver.com/PostView.nhn?blogId=henry9487&logNo=220118976362&parentCategoryNo=&categoryNo=&viewDate=&isShowPopularPosts=false&from=postView  
#### custom npc  
https://github.com/DarkScientist/CustomNPCS/blob/master/src/fr/darkscientist/customnpcs/Listener.java  
### 모델링
https://oortcloud1996.tistory.com/7?category=598654  
### 서버 호스팅
https://harineubo.tistory.com/8?category=792392  
현존하는 서버 버젼 : 1.15.2    
`sudo su`  
해당 디렉토리 들어가서  
`java -Xms500M -Xmx3G -jar paperclip.jar`  
filezila에서 플러그인 넘기기 (넘기기 위해서도 chmod 777 ./plugin 해주어야함  
filezila 호스트 : sftp://34.64.106.161 사용자명 : rsa-key-20200302  
나올 때 exit 하기!
- 오늘 자바 버젼 안 맞아서 개고생한거  
: openjdk9> 13으로 바꿈  
https://codechacha.com/ko/ubuntu-install-open-jdk9/  
### 참조 링크
마크다운 문법 : https://heropy.blog/2017/09/30/markdown/  
D드라이브 powershell 문제 : https://baejangho.com/entry/Windows-PowerShell-env  
좋은 강좌인데 ㅠㅠㅠ 왜 끝나썽요 : http://blog.naver.com/PostList.nhn?blogId=hino1149&categoryNo=0&from=postList65724e7e2d2fefd8989c9abd9c4ebba7cd9f5de4  

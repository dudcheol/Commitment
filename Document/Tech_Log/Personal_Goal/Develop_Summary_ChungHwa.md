# 개발 일지 🐱‍👓

> 청화 개발 일지 

<br>

```
yum 명령어 인식이 안 되서 별 짓을 다 해봤다,,,
결국 apt-get 으로 설치했는데
apt-get도 제대로 안 되서 알아보니 미러사이트를 통해 패키지를 다운받아야 한다.
오래된 미러사이트를 사용해서 자꾸 mirrorlist error가 떴음

국내 미러사이트 List
1. 다음카카오 http://ftp.daumkakao.com/centos
2. 카이스트 http://ftp.kaist.ac.kr/CentOS
3. 네이버 http://mirror.navercorp.com/centos

3가지가 있다.
li /etc/yum으로 mirrorlist 위치를 찾아주고 yum.repos.d에 미러리스트를 넣어주면 됨

근데 이 때, yum 자체가 없어서 wget http://yum.baseurl.org/download/3.4/yum-3.4.3.tar.gz 로
다운을 받아줘야 하는데 오래된 사이트라 안 됨^^

그럴 땐 열심히 구글링해서 직접 tar.gz를 다운 받고 풀어줘서 수동으로 설치...!

```
```
http -> https로 전환하기 위해 let's encrypt로 ssl 인증서(웹서버에서 사용될 무료 https 인증서) 
발급해서 적용해야 함
```

```
어이없는 경험...
조인했을 때 
select * from sns s, commit c
select * from commit c, sns s

순서에 따라서 값이 잘못 매핑될 수도 있다. (둘 다 id column이 있어서 순서에 따라 sns의 id가 
매핑되기도 commit의 id가 매핑되기도 한다.)
```
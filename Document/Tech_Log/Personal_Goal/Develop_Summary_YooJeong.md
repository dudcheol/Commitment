# 개발 일지 🐱‍👓

> 유정 개발 일지 

<br>

무한매핑

@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class)

이걸루 해결

http→https 로 리다이렉트

```
server{
		listen 80;

		location / {
//포트 80 서버블럭에서 301 리다이렉트를 해주면 모든 것을 443 블럭으로 가게 된다.
					return 301 https://ssafy.commitment.kro.kr$request_uri;
			}
}  

server{
			listen 443 ssl;
		  ssl_certificate /certs/certificate.crt;
	    ssl_certificate_key /certs/private.key;
			server_name ssafy.commitment.kro.kr;
      root /var/www/html/dist;
      index index.html;
	 }
	
```

```
routes로 명시된 주소로 이동했을때 404에러뜰경우에는 try_files를 다음과 같이 수정한다.

server {
        listen 80;
        localhost / {
            root    /app/build;
            index   index.html;
            try_files $uri $uri/ /index.html;
        }
    }

```

mariadb export 명령어

mysqldump -uroot -proot ssafy > dump.sql

---

props로 img src전달할떄는 require를 사용해야한다.

require(path)

```
서버 로그 확인

tail -f nohup.out
```
jwt 토큰은 매번 새로발급하자...

jpa 쿼리 from에는 해당 dto 부터 쓰자.....
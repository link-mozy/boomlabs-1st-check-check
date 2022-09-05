NodeJS APP 실행 방법:

1. DB 서버 실행 (_만약 Spring App에서 실행했다면 생략_)

```bash
$ docker run -p {포트번호}:3306 --detach --name check-mariadb --env MARIADB_USER={사용자이름} --env MARIADB_PASSWORD={ROOT비밀번호} --env MARIADB_ROOT_PASSWORD={사용자비밀번호}  mariadb:latest
```

2. config 파일 작성

- config/db-info.json
- config/wallet-info.json

3. 도커 이미지 생성 및 실행

```bash
# 도커 파일 빌드
$ docker build -t check-node .
# 도커 실행
$ docker run -d --name check-node -p 9090:3000 check-node
```

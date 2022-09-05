Spring APP 실행 방법:

1. DB 서버 실행

```bash
$ docker run -p {포트번호}:3306 --detach --name check-mariadb --env MARIADB_USER={사용자이름} --env MARIADB_PASSWORD={ROOT비밀번호} --env MARIADB_ROOT_PASSWORD={사용자비밀번호}  mariadb:latest
```

2. application.yml 파일 작성

```YAML
spring:
  ## DB Setting ##
  datasource:
    driver-class-name: org.mariadb.jdbc.Driver
    url: jdbc:mariadb://{DB서버IP}:{포트번호}/{DB명}?characterEncoding=UTF-8&useTimezone=true&serverTimezone=Asia/Seoul
    username:
    password:

## Mybatis Setting ##
mybatis:
  type-aliases-package: com.boomlabs.checkapp
  mapper-locations: mapper/**/*.xml
  configuration:
    map-underscore-to-camel-case: true

## Log-Back File Select ##
logging:
  config: classpath:logback.xml
```

3. spring app 실행

```bash
$ ./runApp.sh # or
$ source ./runApp.sh
```

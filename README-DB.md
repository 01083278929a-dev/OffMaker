## MariaDB 연결 설정 (server)

`server/src/main/resources/application.yml`은 아래 환경변수를 우선 사용합니다.

- `DB_URL`: 예) `jdbc:mariadb://<HOST>:3306/offmaker_db?characterEncoding=UTF-8&serverTimezone=Asia/Seoul`
- `DB_USERNAME`: 예) `offmaker_app`
- `DB_PASSWORD`: 예) (비밀번호)

### Windows PowerShell 예시

```powershell
$env:DB_URL = "jdbc:mariadb://<HOST>:3306/offmaker_db?characterEncoding=UTF-8&serverTimezone=Asia/Seoul"
$env:DB_USERNAME = "offmaker_app"
$env:DB_PASSWORD = "<PASSWORD>"
.\gradlew.bat bootRun
```

### 참고

- 운영에서는 `root` 계정 대신 **앱 전용 계정**을 생성해 최소 권한만 부여하는 것을 권장합니다.
- MyBatis XML은 기본적으로 `server/src/main/resources/mapper/**/*.xml` 경로를 사용합니다.


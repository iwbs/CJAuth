# CJAuth

Spring REST with OAuth2 example

## Remarks

run setup.sql to setup database


### Step 1
POST http://localhost:8080/CJAuth/oauth/token?grant_type=client_credentials
header: Authorization: Basic am9lMjpxd2Vy (joe2:qwer base64)

### Step 2
POST http://localhost:8080/CJAuth/admin
header: Authorization: Bearer ffc35782-61a6-4e31-8369-e32ab02a9377


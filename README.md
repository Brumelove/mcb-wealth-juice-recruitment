# mcb-wealth-juice-recruitment
to view on swagger http://localhost:8092/university/swagger-ui/index.html#

[![Codacy Badge](https://app.codacy.com/project/badge/Grade/0f3e962d917f4fee89482fe090398838)](https://www.codacy.com/gh/Brumelove/mcb-wealth-juice-recruitment/dashboard?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=Brumelove/mcb-wealth-juice-recruitment&amp;utm_campaign=Badge_Grade)

mvn clean install to build project

To test without authentication switch to a dev env

To use prod env make sure you have keycloak setup,
Get your token by running a POST method to http://localhost:8080/auth/realms/mcb-realm/protocol/openid-connect/token and pass into swagger Authorize button.

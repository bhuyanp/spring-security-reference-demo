# Basic Auth and OAuth2 Login Using Spring Security
In this example you will see how to set up basic login form based 
authentication and oauth login using Google and GitHub. Refer CustomOAuth2UserService
to see how you can enrich the GRANTED AUTHORIZATION of Oauth user by adding roles form your org's user directory.


Update UserDetailsServiceImpl to add your login users. This is just for demo purposes. In real world projects, you would connect to LDAP to fetch users.

### End Points
#### For all authenticated users
http://localhost:8080/
#### For admin users
http://localhost:8080/admin

## Basic Auth
To turn on basic auth example do the followings.

<code>
// uncomment the configuration<br>
@Configuration<br>
public class SecurityConfigBasicAuth {
</code>
<br />
<br />


<code>
// comment the configuration<br>
//@Configuration<br>
public class SecurityConfigOAuth {
</code>

## OAuth
To turn on basic OAuth example do the reverse.

Also, update application.properties for Google and GitHub oauth client details.

####Google:
Follow these basic steps to create google oauth client id/secret
https://developers.google.com/identity/protocols/oauth2#basicsteps

spring.security.oauth2.client.registration.google.client-id=
spring.security.oauth2.client.registration.google.client-secret=

####GitHub:
spring.security.oauth2.client.registration.github.client-id=
spring.security.oauth2.client.registration.github.client-secret=


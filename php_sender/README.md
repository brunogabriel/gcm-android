# PHP - Mock simple server

In this code, exists a global var `GCM_URL` defined by google, until now, works correctly.

### Steps

To use this code, is very, very simple, you need:

1. Change `CREDENTIALS_KEY`,  with your project credentials.

2. Put in `registrationIds`, unique device ids.

3. `androidPush` contains a push message.

4. After it, in a terminal: 

```bash
php simple_server_gcm.php
```

The Google answer should be in your terminal.

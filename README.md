Para lanzar sonar es necesario obtener el token de acceso
y lanzar el siguiente comando:

```bash 
    mvn verify sonar:sonar -D sonar.token=TOKEN_OBTENIDO -f pom.xml
```
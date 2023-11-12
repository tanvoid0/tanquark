# Generation 1

#mkdir jwt
#openssl genrsa -out jwt/rsaPrivateKey.pem 2048
#openssl rsa -pubout -in jwt/rsaPrivateKey.pem -out jwt/publicKey.pem
#openssl pkcs8 -topk8 -nocrypt -inform pem -in jwt/rsaPrivateKey.pem -outform pem -out jwt/privateKey.pem
#chmod 600 jwt/rsaPrivateKey.pem
#chmod 600 jwt/privateKey.pem


# Generation 2
# openssl genrsa -out baseKey.pem
# openssl pkcs8 -topk8 -inform PEM -in baseKey.pem -out privateKey.pem -nocrypt
# openssl rsa -in baseKey.pem -pubout -outform PEM -out publicKey.pem


 # Generation 3
 openssl genrsa -out rsaPrivateKey.pem 2048
 openssl rsa -pubout -in rsaPrivateKey.pem -out publicKey.pem
 openssl pkcs8 -topk8 -nocrypt -inform pem -in rsaPrivateKey.pem -outform pem -out privateKey.pem
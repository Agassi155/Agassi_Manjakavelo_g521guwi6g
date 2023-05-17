# Agassi_Manjakavelo_g521guwi6g


# jwt-student

Ce projet consiste à lister les etudiants avec des filtres en appelant un url proteger par spring security.



## Inscription
Pour enregistrer un nouvel utilisateur, effectuez une requête POST vers 
```
/api/auth/register
```

La requête doit inclure le corps JSON avec firstName,email,password. Exemple :
```
  {
  "firstName": "ctrmddaz",
  "email": "ctdddra",
  "password": "cdddhax"
}

```
Un token sera généré pour l'utilisateur créé

## Liste des étudiants
Pour récupérer une liste d'étudiants avec des filtres optionnels, effectuez une requête POST vers
```
/api/student/list
```


La requête doit inclure le corps JSON avec 

* className Optionnel
* teacherFirstName doit etre identique avec avec teacherLastName 
* teacherLastName doit etre identique avec  teacherFirstName
* page Obligatoire
* size Obligatoire
exemple : 
```
{  
    "className" : "java",
    "teacherFirstName" : "gia",
    "teacherLastName" : "fu",
    "page" : 0,
   "size" : 10
}
```
Veuillez noter que le jeton reçu lors de l'inscription doit être inclus dans l'en-tête d'autorisation de la requête pour l'authentification.

##Dépendances
* Spring Boot
* Spring Security
* JWT (JSON Web Tokens)
* JPA
* PostgreSQL
* lombok

## Source
* YouTube
* Baeldung
* ChatGPT
* Stack Overflow
* 
## Amélioration à faire
* Test unitaire et intégration
* implementation d'un login 
* fonctionnalité refresh token 

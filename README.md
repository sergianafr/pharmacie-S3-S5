# Set up du projet 
## A installer
- apache ant
- extension vscode:  
    - java server pages
    - extension pack for java
    - project manager for java

## A configurer
- Fichier build.xml: modifier cette partie
    <target name="deploy" depends="package">
        <copy file="${dist.dir}/pharmacie.war" todir=*chemin vers tomcat webapps*/>
    </target>

## Commandes utiles
- ant clean deploy: deploie le projet vers tomcat 

## lien pour aller vers le projet:
- localhost:8080\pharmacie

# Structure du projet
## Models:
- Package pour les entités reliées a la base de données 
## Controller
- Package pour mettre les servlet:
    - crée le servlet comme ceci

       @WebServlet("/NomServlet")
       public class NomServlet extends HttpServlet {}

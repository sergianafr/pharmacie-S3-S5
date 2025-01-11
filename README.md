## A installer
- apache ant 

## A configurer
- Fichier build.xml: modifier cette partie
    <target name="deploy" depends="package">
        <copy file="${dist.dir}/pharmacie.war" todir=*chemin vers tomcat webapps*/>
    </target>

## Commandes utiles
- ant clean deploy: deploie le projet vers tomcat 
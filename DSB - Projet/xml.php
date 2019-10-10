<link rel='stylesheet' href='css/xml.css' />
<?php
define("SERVERNAME","localhost");//mysql.istic.univ-rennes1.fr
define("USERNAME","root");//user_18002886
define("PASSWORD","");//Rodrigue le plus beau
define("DATABASE","dsb");//base18002886

$query0 = "SELECT `Titre`,`Date_`
           FROM `tableau`
           INNER JOIN `mouvement`ON `tableau`.`Nom_Mouvement` = `mouvement`.`Nom`
           INNER JOIN `modele`ON `tableau`.`Modele_ID` = `modele`.`ID`
           INNER JOIN `peintre`ON `tableau`.`Peintre_ID` = `peintre`.`ID`";

$query1 ="SELECT `Nom_Mouvement`,`Description`,`Siecle`
         FROM `tableau`
         INNER JOIN `mouvement`ON `tableau`.`Nom_Mouvement` = `mouvement`.`Nom`
         INNER JOIN `modele`ON `tableau`.`Modele_ID` = `modele`.`ID`
         INNER JOIN `peintre`ON `tableau`.`Peintre_ID` = `peintre`.`ID`";

$query2 = "SELECT `Longueur`,`Largeur`, `Technique`
           FROM `tableau`
           INNER JOIN `mouvement`ON `tableau`.`Nom_Mouvement` = `mouvement`.`Nom`
           INNER JOIN `modele`ON `tableau`.`Modele_ID` = `modele`.`ID`
           INNER JOIN `peintre`ON `tableau`.`Peintre_ID` = `peintre`.`ID`";

$query3 = "SELECT peintre.`nom`,`ybirth`, `ydeath`
           FROM `tableau`
           INNER JOIN `mouvement`ON `tableau`.`Nom_Mouvement` = `mouvement`.`Nom`
           INNER JOIN `modele`ON `tableau`.`Modele_ID` = `modele`.`ID`
           INNER JOIN `peintre`ON `tableau`.`Peintre_ID` = `peintre`.`ID`";

try {
  $bd = new PDO("mysql:host=".SERVERNAME.";dbname=".DATABASE.";charset=utf8", USERNAME , PASSWORD);

  //$bd->setAttribute(PDO::ATTR_ERRMOD,PDO::ERRMODE_EXCEPTION);

$table0=$bd->query($query0);
$table1=$bd->query($query1);
$table2=$bd->query($query2);
$table3=$bd->query($query3);

$table0->setFetchMode(PDO::FETCH_ASSOC);
$table1->setFetchMode(PDO::FETCH_ASSOC);
$table2->setFetchMode(PDO::FETCH_ASSOC);
$table3->setFetchMode(PDO::FETCH_ASSOC);

$imp = new DOMImplementation;
$dtd = $imp -> createDocumentType('galerie','','data/galerie.dtd');
$xml = $imp -> createDocument('','',$dtd);

$galerie = $xml -> createElement("galerie");

while($nuplet0 = $table0 -> fetch()){
    $tableau = $xml -> createElement("tableau");
    foreach($nuplet0 as $attribut => $valeur){
        $element = $xml -> createElement($attribut, $valeur);
        $element = $tableau -> appendChild($element);
    }
    $mouvement = $xml -> createElement("mouvement");
    $nuplet1=$table1->fetch();
    foreach($nuplet1 as $attribut => $valeur) {
        $element2 = $xml -> createElement($attribut,$valeur);
        $element2 = $mouvement -> appendChild($element2);
    }
    $modele = $xml -> createElement("modele");
    $nuplet2=$table2->fetch();
    foreach($nuplet2 as $attribut => $valeur) {
        $element3 = $xml -> createElement($attribut,$valeur);
        $element3 = $modele -> appendChild($element3);
    }
    $peintre = $xml -> createElement("peintre");
    $nuplet3=$table3->fetch();
    foreach($nuplet3 as $attribut => $valeur) {
        $element4 = $xml -> createElement($attribut,$valeur);
        $element4 = $peintre -> appendChild($element4);
    }
    $tableau -> appendChild($peintre);
    $tableau -> appendChild($modele);
    $tableau -> appendChild($mouvement);
    $galerie-> appendChild($tableau);
}

$xml -> appendChild($galerie);

if($xml->validate()){
  $xml->formatOutput = TRUE;
  $xml -> save('galerie.xml');
  echo $xml -> saveXML();
} else {
  echo "Error";
}


echo"<p align='center'><a href='accueil.html'> Accueil </a> </p>";
}
catch(PDOException $e){
  echo "Erreur : " .$e->getMessage();
}
?>

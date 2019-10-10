<!DOCTYPE HTMl>
<html>
<head>
<title> Projet DSB </title>
<link rel="stylesheet" href="qer.css" />
<meta charset="UTF-8" />
</head>
<body>
<div class="menu">
<?php
define("SERVERNAME","localhost");//mysql.istic.univ-rennes1.fr
define("USERNAME","root");//18002286
define("PASSWORD","");//Rodrigue le plus beau
define("DATABASE","dsb");//base_18002886

$query = "SELECT `Titre`
          FROM `tableau`
          WHERE(SELECT `ID`
	              FROM `modele`
	              WHERE `Technique`='Huile sur Toile'
	              AND `tableau`.`Modele_ID`=`modele`.`ID`)";
try {
  $bd = new PDO("mysql:host=".SERVERNAME.";dbname=".DATABASE.";charset=utf8", USERNAME , PASSWORD);

  //$bd->setAttribute(PDO::ATTR_ERRMOD,PDO::ERRMODE_EXCEPTION);

$table=$bd->query($query);

$table->setFetchMode(PDO::FETCH_ASSOC);
echo "<p align='center'>$query</p>";
echo "<table border='1' align='center'>";

$nuplet=$table->fetch();

echo"<tr>";

  foreach ($nuplet as $attribut => $valeur) {
    echo "<th>".$attribut."</th>";
  }

  do {
    echo"<tr>";
    foreach ($nuplet as $attribut => $valeur) {
      echo "<td>".$valeur."</td>";
    }
    echo "</tr>";
  } while ($nuplet=$table->fetch());
  echo "</table>";
}
catch(PDOException $e){
  echo "Erreur : " .$e->getMessage();
}
?>
</br>
<p align="center"><a href="../menureq.html"> Menu </a> </p>
</div>
</body>
</html>

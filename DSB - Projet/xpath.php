<!DOCTYPE HTMl>
<html>
<head>
<title> Projet DSB </title>
<link rel="stylesheet" href="start.css" />
<meta charset="UTF-8" />
</head>
<body>
<div class="menu">
  <?php
try {
$xml = new DOMDocument("1.0","UTF-8");
$xml -> load('galerie.xml');
$xpath = new DOMXPath($xml);
$elements = $xpath->query('/galerie/tableau/peintre[ybirth>1900 and ydeath<2000]/nom');

if($xml->validate()){
  if(!is_null($elements)){
    foreach($elements as $element) {
      echo "</br>[".$element->nodeName."]";
      $nodes = $element->childNodes;
      foreach ($nodes as $node) {
        echo $node->nodeValue. "\n";
      }
    }
  } else {
    echo "No results";
  }

} else {
  echo "XML Error";
}

}
catch(PDOException $e){
  echo "Erreur : " .$e->getMessage();
}
?>
</br>
<p align="center"><a href="accueil.html"> Menu </a> </p>
</div>
</body>
</html>

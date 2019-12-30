package fr.istic.prg1.tree;

import java.util.Scanner;

import fr.istic.prg1.tree_util.AbstractImage;
import fr.istic.prg1.tree_util.Iterator;
import fr.istic.prg1.tree_util.Node;
import fr.istic.prg1.tree_util.NodeType;
import fr.istic.prg1.tree.BinaryTree;

/**
 * @author Mickaël Foursov <foursov@univ-rennes1.fr>
 * @version 5.0
 * @since 2016-04-20
 * 
 *        Classe décrivant les images en noir et blanc de 256 sur 256 pixels
 *        sous forme d'arbres binaires.
 * 
 */

public class Image extends AbstractImage {
	private static final Scanner standardInput = new Scanner(System.in);

	public Image() {
		super();
	}

	public static void closeAll() {
		standardInput.close();
	}

	/**
	 * @param x
	 *            abscisse du point
	 * @param y
	 *            ordonnée du point
	 * @pre !this.isEmpty()
	 * @return true, si le point (x, y) est allumé dans this, false sinon
	 */
	@Override
	public boolean isPixelOn(int x, int y) {
		Iterator<Node> it = this.iterator();
		boolean horizontal = true;
		//Creation d'un quadruple de coordonn�es representant l'intervalle entier de l'image
		int X = 128,Y = 128;
		int Xmax =128, Ymax = 128;	
		while(it.nodeType()!=NodeType.LEAF){
					if(horizontal) {
						if(y<Y) {
							it.goLeft();
							Y-=Ymax/2;
						}else{
							it.goRight();
							Y+=Ymax/2;
						}
						Ymax/=2;
						horizontal = false;
					} else {
						if(x<X){
							it.goLeft();
							X-=Xmax/2;
						} else{
							it.goRight();
							X+=Xmax/2;
						}
						Xmax/=2;
						horizontal = true;
					}
		}
		return (it.getValue().state==1);
	}

	/**
	 * this devient identique à image2.
	 *
	 * @param image2
	 *            image à copier
	 *
	 * @pre !image2.isEmpty()
	 */
	@Override
	public void affect(AbstractImage image2) {
		Iterator<Node> it = this.iterator();
		Iterator<Node> it2 = image2.iterator();
		//On nettoie notre image
		it.clear();
		//Si, l'image mis en parametre est non vide, alors on appelle une fct axuiliaire
		if(!it2.isEmpty()) {
			affectAux(it,it2);
		}
	}
	
	/**
	 * Fonction auxiliaire recursive qui affecte le noeud courant de it2 a it, puis effectue appelle recursif a gauche et a droite
	 * @param it, iterator de this
	 * @param it2, iterator de l'image
	 */
	private void affectAux(Iterator<Node> it, Iterator<Node> it2) {
		if(!it2.isEmpty()) {
			//Ajout du Noeud courant
			it.addValue(it2.getValue());
			//On descend a gauche
			it.goLeft();
			it2.goLeft();
			//On appelle recursivement sur le noeud gauche
			affectAux(it,it2);
			//On remonte
			it.goUp();
			it2.goUp();
			//On descend a droite
			it.goRight();
			it2.goRight();
			// On appelle recusivement sur le noeud droit
			affectAux(it,it2);
			//On remonte
			it.goUp();
			it2.goUp();
		}
	}

	/**
	 * this devient rotation de image2 à 180 degrés.
	 *
	 * @param image2
	 *            image pour rotation
	 * @pre !image2.isEmpty()
	 */
	@Override
	public void rotate180(AbstractImage image2) {
		Iterator <Node> it1 = this.iterator();
		Iterator<Node> it2 = image2.iterator();
		it1.clear();
		if(!it2.isEmpty()) {
			rotate180Aux(it1,it2);
		}
	}

	/**
	 * TODO
	 * @param it1
	 * @param it2
	 */
	private void rotate180Aux(Iterator<Node> it1, Iterator<Node> it2) {
		if(!it2.isEmpty()) {
			it1.addValue(it2.getValue());
			
			it1.goLeft();
			it2.goRight();
			
			rotate180Aux(it1,it2);
			
			it1.goUp();
			it2.goUp();
			
			it1.goRight();
			it2.goLeft();
			
			rotate180Aux(it1,it2);
			
			it1.goUp();
			it2.goUp();
		}
	}
			
	/**		
	 * this devient rotation de image2 à 90 degrés dans le sens des aiguilles
	 * d'une montre.
	 *
	 * @param image2
	 *            image pour rotation
	 * @pre !image2.isEmpty()
	 */
	@Override
	public void rotate90(AbstractImage image2) {
		System.out.println();
		System.out.println("-------------------------------------------------");
		System.out.println("Fonction non demeand�e");
		System.out.println("-------------------------------------------------");
		System.out.println();	    
	}

	/**
	 * this devient inverse vidéo de this, pixel par pixel.
	 *
	 * @pre !image.isEmpty()
	 */
	@Override
	public void videoInverse() {
		Iterator<Node> it = this.iterator();
		if(!it.isEmpty()){
			videoInverseAux(it);
		}
	}

	private void videoInverseAux(Iterator<Node> it) {
		if(!it.isEmpty()) {
			if(it.getValue().state==0) {
				it.setValue(Node.valueOf(1));
			} else if(it.getValue().state==1) {
				it.setValue(Node.valueOf(0));
			}
			
			it.goLeft();
			videoInverseAux(it);
			it.goUp();
			it.goRight();
			videoInverseAux(it);
			it.goUp();
		}
	}
	/**
	 * this devient image miroir verticale de image2.
	 *
	 * @param image2
	 *            image à agrandir
	 * @pre !image2.isEmpty()
	 */
	@Override
	public void mirrorV(AbstractImage image2) {
		Iterator<Node> it = this.iterator();
		Iterator<Node> it2 = image2.iterator();
		it.clear();
		if(!(it2.isEmpty())) {
			mirrorVAux(it,it2,1);
		}
	}

	/**
	 * TODO
	 * @param it
	 * @param it2
	 * @param height
	 */
	private void mirrorVAux(Iterator<Node> it, Iterator<Node> it2, int height) {
		it.addValue(it2.getValue());
		if(it2.nodeType() != NodeType.LEAF) {
				
				it2.goLeft();
				if(height%2==1) {
					it.goRight();
				} else {
					it.goLeft();
				}
				
				height++;
				
				mirrorVAux(it,it2, height);
				
				it.goUp();
				it2.goUp();
				
				height--;
				
				
				it2.goRight();
				
				if(height%2==1) {
					it.goLeft();
				} else {
					it.goRight();
				}
				
				height++;
				mirrorVAux(it,it2,height);
				
				it.goUp();
				it2.goUp();
				
				height--;
		}
	}
	/**
	 * this devient image miroir horizontale de image2.
	 *
	 * @param image2
	 *            image à agrandir
	 * @pre !image2.isEmpty()
	 */
	@Override
	public void mirrorH(AbstractImage image2) {
		Iterator<Node> it = this.iterator();
		Iterator<Node> it2 = image2.iterator();
		it.clear();
		if(!(it2.isEmpty())) {
			mirrorHAux(it,it2,1);
		}
	}

	/**
	 * TODO
	 * @param it
	 * @param it2
	 * @param height
	 */
	private void mirrorHAux(Iterator<Node> it, Iterator<Node> it2, int height) {
		it.addValue(it2.getValue());
		if(it2.nodeType() != NodeType.LEAF) {
			
			it2.goLeft();
			if(height%2==0) {
				it.goRight();
			} else {
				it.goLeft();
			}
			
			height++;
			
			mirrorHAux(it,it2, height);
			
			it.goUp();
			it2.goUp();
			
			height--;
			
			
			it2.goRight();
			
			if(height%2==0) {
				it.goLeft();
			} else {
				it.goRight();
			}
			
			height++;
			mirrorHAux(it,it2,height);
			
			it.goUp();
			it2.goUp();
			
			height--;
		}
	}
	
	/**
	 * this devient quart supérieur gauche de image2.
	 *
	 * @param image2
	 *            image à agrandir
	 * 
	 * @pre !image2.isEmpty()
	 */
	@Override
	public void zoomIn(AbstractImage image2) {
		Iterator<Node> it = this.iterator();
		Iterator<Node> it2 = image2.iterator();
		it.clear();
		if(!it2.isEmpty()) {
			if(it2.nodeType()!=NodeType.LEAF) {
				it2.goLeft();
				if(it2.nodeType()!=NodeType.LEAF) {
					it2.goLeft();
					affectAux(it,it2);
				} else {
					affectAux(it,it2);
				}
			} else {
				affectAux(it,it2);
			}
		}
	}
	/**
	 * Le quart supérieur gauche de this devient image2, le reste de this
	 * devient éteint.
	 * 
	 * @param image2
	 *            image à réduire
	 * @pre !image2.isEmpty()
	 */
	@Override
	public void zoomOut(AbstractImage image2) {
		Iterator<Node> it = this.iterator();
		Iterator<Node> it2 = image2.iterator();
		it.clear();
		it.addValue(Node.valueOf(2));
		it.goRight();
		it.addValue(Node.valueOf(0));
		it.goUp();
		it.goLeft();
		it.addValue(Node.valueOf(2));
		it.goRight();
		it.addValue(Node.valueOf(0));
		it.goUp();
		it.goLeft();
		if(!it2.isEmpty()) {
			zoomOutAux(it,it2,3);
			 if (it.getValue().state == 0) {
		            it.goRoot();
		            it.clear();
		            it.addValue(Node.valueOf(0));
		     } 
		}
	}	
	
	private void zoomOutAux(Iterator<Node> it, Iterator<Node> it2, int level ) {
		 int right, left;
			 if (level < 17) {
				 if (it2.nodeType().equals(NodeType.DOUBLE)) {
					 it.addValue(Node.valueOf(it2.getValue().state));
		             it.goLeft();
		             it2.goLeft();
		             zoomOutAux(it, it2, level + 1);
		             left = it.getValue().state;
		             it.goUp();
		             it2.goUp();
		             it.goRight();
		             it2.goRight();
		             zoomOutAux(it, it2, level + 1);
		             right = it.getValue().state;
		             it.goUp();
		             it2.goUp();
		             if (left == right && right != 2){
		            	 it.clear();
		                 it.addValue(Node.valueOf(right));
		              }
				 } else {
					 it.addValue(it2.getValue());
				 }
		 
		    }else{
		        if(it2.nodeType().equals(NodeType.DOUBLE)){
		        	it2.goLeft();
		            left = it2.getValue().state;
		            it2.goUp();
		            it2.goRight();
		            right = it2.getValue().state;
		            it2.goUp();
		            if ((left == 0 && right == 0) || (left == 0 && right == 2) || (left == 2 && right == 0)) {
		            	it.addValue(Node.valueOf(0));
		            } else {
		            	it.addValue(Node.valueOf(1));
		            }
		        }else {
		        	it.addValue(Node.valueOf(it2.getValue().state));
		        }
		    }
	}
	
	/**
	 * this devient l'intersection de image1 et image2 au sens des pixels
	 * allumés.
	 * 
	 * @pre !image1.isEmpty() && !image2.isEmpty()
	 * 
	 * @param image1 premiere image
	 * @param image2 seconde image
	 */
	@Override
	public void intersection(AbstractImage image1, AbstractImage image2) {
		Iterator<Node> it= this.iterator();
		Iterator<Node> it2= image1.iterator();
		Iterator<Node> it3= image2.iterator();
		it.clear();
		if(!it2.isEmpty() && !it3.isEmpty()) {
			intersectionAux(it,it2,it3);
		}
	}

	
	private void intersectionAux(Iterator<Node> it, Iterator<Node> it2, Iterator<Node> it3) {
		if(!it2.isEmpty() && !it3.isEmpty()) {
			Node n1 = it2.getValue();
			Node n2 = it3.getValue();
			if(n1.state==0 || n2.state==0) {
				it.addValue(Node.valueOf(0));
			} else if (n1.state==1 && n2.state==1) {
				it.addValue(Node.valueOf(1));
			} else if (n1.state==2 || n2.state==2) {
				if(n1.state==2 && n2.state==1) {
					affectAux(it,it2);
				} else if(n1.state==1 && n2.state==2) {
					affectAux(it,it3);
				} else {
					it.addValue(Node.valueOf(2));
						
					it.goLeft();
					it2.goLeft();
					it3.goLeft();
					
					intersectionAux(it,it2,it3);
					int v1 = it.getValue().state;
					
					
					it.goUp();
					it2.goUp();
					it3.goUp();
					
					it.goRight();
					it2.goRight();
					it3.goRight();
					
					intersectionAux(it,it2,it3);
					int v2 = it.getValue().state;
					
					it.goUp();
					it2.goUp();
					it3.goUp();
					
					if(v1==v2 && v1!=2) {
						it.clear();
						it.addValue(Node.valueOf(v1));
					}
				}
				
			}
		}
	}
	/**
	 * this devient l'union de image1 et image2 au sens des pixels allumés.
	 * 
	 * @pre !image1.isEmpty() && !image2.isEmpty()
	 * 
	 * @param image1 premiere image
	 * @param image2 seconde image
	 */
	@Override
	public void union(AbstractImage image1, AbstractImage image2) {
		Iterator<Node> it = this.iterator();
		Iterator<Node> it2 = image1.iterator();
		Iterator<Node> it3 = image2.iterator();
		it.clear();
		if(!it2.isEmpty() && !it3.isEmpty()) {
			UnionAux(it,it2,it3);
		} else if (it2.isEmpty() && !it3.isEmpty()) {
			affectAux(it,it3);
		} else if(!it2.isEmpty() && it3.isEmpty()) {
			affectAux(it,it2);
	    }
	}
	
	/**
	 * TODO
	 * @param it
	 * @param it2
	 * @param it3
	 */
	private void UnionAux(Iterator<Node> it, Iterator<Node> it2, Iterator<Node> it3) {
		if(!it2.isEmpty() && !it3.isEmpty()) {
			Node n1 = it2.getValue();
			Node n2 = it3.getValue();
			if(n1.state==1 || n2.state==1) {
				it.addValue(Node.valueOf(1));
			} else if (n1.state==0 && n2.state==0) {
				it.addValue(Node.valueOf(0));
			} else if (n1.state==2 || n2.state==2) {
				if(n1.state==2 && n2.state==0) {
					affectAux(it,it2);
				} else if(n1.state==0 && n2.state==2) {
					affectAux(it,it3);
				} else {
					it.addValue(Node.valueOf(2));
						
					it.goLeft();
					it2.goLeft();
					it3.goLeft();
					
					UnionAux(it,it2,it3);
					int v1 = it.getValue().state;
					
					
					it.goUp();
					it2.goUp();
					it3.goUp();
					
					it.goRight();
					it2.goRight();
					it3.goRight();
					
					UnionAux(it,it2,it3);
					int v2 = it.getValue().state;
					
					it.goUp();
					it2.goUp();
					it3.goUp();
					
					if(v1==v2 && v1!=2) {
						it.clear();
						it.addValue(Node.valueOf(v1));
					}
				}
				
			}
		}
	}
	
	/**
	 * Attention : cette fonction ne doit pas utiliser la commande isPixelOn
	 * 
	 * @return true si tous les points de la forme (x, x) (avec 0 <= x <= 255)
	 *         sont allumés dans this, false sinon
	 */
	@Override
	public boolean testDiagonal() {
		Iterator<Node> it = this.iterator();
	    if(!it.isEmpty()) {
	    	testDiagonalAux(it,0,0,255,1);
	    	return it.getValue().state == 1;
	    } else {
	    	return false; //Image vide 
	    }
	}

	
	private void testDiagonalAux(Iterator<Node> it, int x, int min, int max, int height) {
		if (it.nodeType().equals(NodeType.DOUBLE)) {
            int  milieu = (min + max)/2;
            if (height%2 == 0) {
                if (x <= milieu){
                    it.goLeft();
                    testDiagonalAux(it, x, min, max, height+1);
                } else {
                    it.goRight();
                    testDiagonalAux(it, x, min+1, max, height+1);
                }
            } else {
            	if(x <= milieu) {
                    it.goLeft();
            	}else{
                    it.goRight();
            	}
            	testDiagonalAux(it, x, min, max, height+1);
            }
        } else if (it.getValue().state == 1 && x <= 255) {
            it.goRoot();
            testDiagonalAux(it, x + 1, 0, 255, 1);
        } 
	}
	/**
	 * @param x1
	 *            abscisse du premier point
	 * @param y1
	 *            ordonnée du premier point
	 * @param x2
	 *            abscisse du deuxième point
	 * @param y2
	 *            ordonnée du deuxième point
	 * @pre !this.isEmpty()
	 * @return true si les deux points (x1, y1) et (x2, y2) sont représentés par
	 *         la même feuille de this, false sinon
	 */
	@Override
	public boolean sameLeaf(int x1, int y1, int x2, int y2) {
		Iterator<Node> it = this.iterator();
		boolean horizontal = true;
		int X = 128, Y = 128;
		int Xmax = 128, Ymax = 128;	
		while(it.nodeType()!=NodeType.LEAF){
					if(horizontal) {
						if(y1<Y && y2<Y) {
							it.goLeft();
							Y-=Ymax/2;
						}else if(y1>=Y && y2>=Y){
							it.goRight();
							Y+=Ymax/2;
						} else {
							return false;
						}
						Ymax/=2;
						horizontal = false;
					} else {
						if(x1<X && x2<X){
							it.goLeft();
							X-=Xmax/2;
						} else if(x1>=X && x2>=X){
							it.goRight();
							X+=Xmax/2;
						} else {
							return false;
						}
						Xmax/=2;
						horizontal = true;
					}
		}
		return true;
	}

	/**
	 * @param image2
	 *            autre image
	 * @pre !this.isEmpty() && !image2.isEmpty()
	 * @return true si this est incluse dans image2 au sens des pixels allumés
	 *         false sinon
	 */
	@Override
	public boolean isIncludedIn(AbstractImage image2) {
		Iterator<Node> it = this.iterator();
		Iterator<Node> it2 = image2.iterator();
		if(it2.isEmpty()) {
			return false;
		} else {
			return isIncludeInAux(it,it2);
		}
	}
	
	private boolean isIncludeInAux(Iterator<Node> it, Iterator<Node> it2) {
		if(!it2.isEmpty() && !it.isEmpty()) {
			if(it.getValue().state==1 && (it2.getValue().state==2 || it2.getValue().state==0) ) {
				return false;
			} else if(it.getValue().state==2 && (it2.getValue().state==0)) {
				return false;
			} else {
				it.goLeft();
				it2.goLeft();
				if(isIncludeInAux(it,it2)) {
					it.goUp();
					it2.goUp();
					it.goRight();
					it2.goRight();
					if(!isIncludeInAux(it,it2)) {
						return false;
					}
				} else {
					return false;
				}
				it.goUp();
				it2.goUp();
			}
		}
		return true;
	}
	

}

package bowling;

import java.util.HashMap;
import java.util.Map;

public class PartieMultiJoueurs implements IPartieMultiJoueurs  {
    private String[]nomsDesJoueurs;
    private int tourCourant;
    private int joueurCourant;
    private Map<String, PartieMonoJoueur> partie;


    public String demarreNouvellePartie(String[] nomsDesJoueurs) throws IllegalArgumentException {
        if (nomsDesJoueurs == null || nomsDesJoueurs.length == 0) {
            throw new IllegalArgumentException("Le tableau de joueurs ne peut pas être vide ou null.");
        }
        this.partie = new HashMap<>();
		this.joueurCourant = nomsDesJoueurs.length;;
		this.tourCourant = 0;

        for (String joueur : nomsDesJoueurs) {
            partie.put(joueur, new PartieMonoJoueur());
        }

        return "Prochain tir: joueur " + nomsDesJoueurs[tourCourant] + ", tour n° " + partie.get(nomsDesJoueurs[tourCourant]).numeroTourCourant() + ", boule n° " + partie.get(nomsDesJoueurs[tourCourant]).numeroProchainLancer();
    }


    public String enregistreLancer(int nombreDeQuillesAbattues) throws IllegalStateException {
        if (PartieFini()) throw  new IllegalStateException("La partie est terminée");  
        PartieMonoJoueur partieJoueur = partie.get(nomsDesJoueurs[tourCourant]);
		partieJoueur.enregistreLancer(nombreDeQuillesAbattues);
		if (partieJoueur.numeroProchainLancer() == 1 || partieJoueur.estTerminee()) tourCourant = (tourCourant+1)%joueurCourant;
		if (PartieFini()) return "Partie terminée";

		return "Prochain tir: joueur " + nomsDesJoueurs[tourCourant] + ", tour n° " + partie.get(nomsDesJoueurs[tourCourant]).numeroTourCourant() + ", boule n° " + partie.get(nomsDesJoueurs[tourCourant]).numeroProchainLancer();
	}

    
        
        public int scorePour(String nomDuJoueur) throws IllegalArgumentException {
            PartieMonoJoueur partieJoueur = partie.get(nomDuJoueur);
            if (partieJoueur == null) throw new IllegalArgumentException("Joueur inexistant");
            return partieJoueur.score();
        }


    public boolean PartieFini(){
        return partie.get(nomsDesJoueurs[joueurCourant-1]).estTerminee();
    }
}

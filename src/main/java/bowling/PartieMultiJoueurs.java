package bowling;

import java.util.HashMap;
import java.util.Map;

public class PartieMultiJoueurs implements IPartieMultiJoueurs {
	private Map<String, PartieMonoJoueur> Partie;
	private String[] nomsDesJoueurs;
	private int joueurCourant;
	private int tourCourant;

	@Override
	public String demarreNouvellePartie(String[] nomsDesJoueurs) throws IllegalArgumentException {
		if (nomsDesJoueurs.length == 0) throw new IllegalArgumentException("Il faut au minimum 1 joueur");
		Partie = new HashMap<>();
		joueurCourant = nomsDesJoueurs.length;
		tourCourant = 0;
		this.nomsDesJoueurs = nomsDesJoueurs;
		for (String nom : nomsDesJoueurs) {
			Partie.put(nom, new PartieMonoJoueur());
		}
		return "Prochain tir: joueur " + nomsDesJoueurs[tourCourant] + ", tour n° " + Partie.get(nomsDesJoueurs[tourCourant]).numeroTourCourant() + ", boule n° " + Partie.get(nomsDesJoueurs[tourCourant]).numeroProchainLancer();
	}

	@Override
	public String enregistreLancer(int nombreDeQuillesAbattues) throws IllegalStateException {
		if (PartieFini()) throw new IllegalStateException("La partie est terminée");
		PartieMonoJoueur partieJoueur = Partie.get(nomsDesJoueurs[tourCourant]);
		partieJoueur.enregistreLancer(nombreDeQuillesAbattues);
		if (partieJoueur.numeroProchainLancer() == 1 || partieJoueur.estTerminee()) tourCourant = (tourCourant + 1) % joueurCourant;
		if (PartieFini()) return "Partie terminée";
		return "Prochain tir: joueur " + nomsDesJoueurs[tourCourant] + ", tour n° " + Partie.get(nomsDesJoueurs[tourCourant]).numeroTourCourant() + ", boule n° " + Partie.get(nomsDesJoueurs[tourCourant]).numeroProchainLancer();
	}

	@Override
	public int scorePour(String nomDuJoueur) throws IllegalArgumentException {
		PartieMonoJoueur partieJoueur = Partie.get(nomDuJoueur);
		if (partieJoueur == null) throw new IllegalArgumentException("Joueur inexistant");
		return partieJoueur.score();
	}

	public boolean PartieFini() {
		return Partie.get(nomsDesJoueurs[joueurCourant - 1]).estTerminee();
	}
}
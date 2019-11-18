package binfa;

import java.io.FileNotFoundException;

public class LZWBinFa {
	/*
	 * public:
	 *
	 */
	/*
	 * Szemben a bin�ris keres�f�nkkal (BinFa oszt�ly)
	 * http://progpater.blog.hu/2011/04/12/
	 * imadni_fogjak_a_c_t_egy_emberkent_tiszta_szivbol_3 itt (LZWBinFa oszt�ly) a
	 * fa gy�kere nem pointer, hanem a '/' bet�t tartalmaz� objektum, l�sd majd a
	 * v�dett tagok k�z�tt lent: Csomopont gyoker; A fa viszont m�r pointer, mindig
	 * az �p�l� LZW-f�nk azon csom�pontj�ra mutat, amit az input feldolgoz�sa sor�n
	 * az LZW algoritmus logik�ja dikt�l:
	 * http://progpater.blog.hu/2011/02/19/gyonyor_a_tomor Ez a konstruktor annyit
	 * csin�l, hogy a fa mutat�t r��ll�tja a gy�k�rre. (Mert ugye laboron, blogon,
	 * el�ad�sban tiszt�ztuk, hogy a tartalmazott tagok, most "Csomopont gyoker"
	 * konstruktora el�bb lefut, mint a tagot tartalmaz� LZWBinFa oszt�ly
	 * konstruktora, �ppen a k�vetkez�, azaz a fa=&gyoker OK.)
	 */
	/*
	 * LZWBinFa ():fa (&gyoker) { } ~LZWBinFa () { szabadit (gyoker.egyesGyermek
	 * ()); szabadit (gyoker.nullasGyermek ()); }
	 */

	public LZWBinFa() {

		fa = gyoker;

	}
	/*
	 * Tagf�ggv�nyk�nt t�lterhelj�k a << oper�tort, ezzel a c�lunk, hogy felkelts�k
	 * a hallgat� �rdekl�d�s�t, mert ekkor �gy nyomhatjuk a f�ba az inputot: binFa
	 * << b; ahol a b egy '0' vagy '1'-es bet�. Mivel tagf�ggv�ny, �gy van r�
	 * "�rtelmezve" az aktu�lis (this "rejtett param�terk�nt" kapott ) p�ld�ny, azaz
	 * annak a f�nak amibe �ppen be akarjuk nyomni a b bet�t a tagjai (pl.: "fa",
	 * "gyoker") haszn�lhat�ak a f�ggv�nyben.
	 * 
	 * A f�ggv�nybe programoztuk az LZW fa �p�t�s�nek algoritmus�t tk.:
	 * http://progpater.blog.hu/2011/02/19/gyonyor_a_tomor
	 * 
	 * a b form�lis param az a bet�, amit �ppen be kell nyomni a f�ba.
	 * 
	 * a binFa << b (ahol a b majd a v�g�n l�tszik, hogy m�r az '1' vagy a '0') azt
	 * jelenti tagf�ggv�nyk�nt, hogy binFa.operator<<(b) (glob�lisk�nt �gy festene:
	 * operator<<(binFa, b) )
	 * 
	 */
	/*
	 * void operator<< (char b) { // Mit kell betenni �ppen, '0'-t? if (b == '0') {
	 * 
	 */

	public void egyBitFeldolg(char b) {
		// Mit kell betenni �ppen, '0'-t?
		if (b == '0') {
			/*
			 * Van '0'-s gyermeke az aktu�lis csom�pontnak? megk�rdezz�k T�le, a "fa" mutat�
			 * �ppen re� mutat
			 */
			/*
			 * if (!fa->nullasGyermek ()) // ha nincs, h�t akkor csin�lunk { // elk�sz�tj�k,
			 * azaz p�ld�nyos�tunk a '0' bet� akt. parammal Csomopont *uj = new Csomopont
			 * ('0'); // az aktu�lis csom�pontnak, ahol �llunk azt �zenj�k, hogy // jegyezze
			 * m�r be mag�nak, hogy null�s gyereke mostant�l van // k�ldj�k is Neki a gyerek
			 * c�m�t: fa->ujNullasGyermek (uj); // �s vissza�llunk a gy�k�rre (mert ezt
			 * dikt�lja az alg.) fa = &gyoker; } else // ha van, arra r�l�p�nk { // azaz a
			 * "fa" pointer m�r majd a sz�ban forg� gyermekre mutat: fa = fa->nullasGyermek
			 * (); } }
			 *
			 */
			if (fa.nullasGyermek() == null) // ha nincs, h�t akkor csin�lunk
			{
				// elk�sz�tj�k, azaz p�ld�nyos�tunk a '0' bet� akt. parammal
				Csomopont uj = new Csomopont('0');
				// az aktu�lis csom�pontnak, ahol �llunk azt �zenj�k, hogy
				// jegyezze m�r be mag�nak, hogy null�s gyereke mostant�l van
				// k�ldj�k is Neki a gyerek c�m�t:
				fa.ujNullasGyermek(uj);
				// �s vissza�llunk a gy�k�rre (mert ezt dikt�lja az alg.)
				fa = gyoker;
			} else // ha van, arra r�l�p�nk
			{
				// azaz a "fa" pointer m�r majd a sz�ban forg� gyermekre mutat:
				fa = fa.nullasGyermek();
			}
		} // Mit kell betenni �ppen, vagy '1'-et?
		/*
		 * else { if (!fa->egyesGyermek ()) { Csomopont *uj = new Csomopont ('1');
		 * fa->ujEgyesGyermek (uj); fa = &gyoker; } else { fa = fa->egyesGyermek (); } }
		 * }
		 *
		 */ else {
			if (fa.egyesGyermek() == null) {
				Csomopont uj = new Csomopont('1');
				fa.ujEgyesGyermek(uj);
				fa = gyoker;
			} else {
				fa = fa.egyesGyermek();
			}
		}
	}
	/*
	 * A bej�r�ssal kapcsolatos f�ggv�nyeink (t�lterhelt kiir-�k, atlag, ratlag
	 * stb.) rekurz�vak, tk. a rekurz�v fabej�r�st val�s�tj�k meg (l�sd a 3. el�ad�s
	 * "Fabej�r�s" c. f�li�j�t �s t�rsait)
	 * 
	 * (Ha a rekurz�v f�ggv�nnyel �ltal�ban gondod van => K&R k�nyv megfelel� r�sze:
	 * a 3. ea. izometrikus r�sz�ben ezt "let�ncoltuk" :) �s k�l�n id�zt�k a K&R
	 * �ll�spontj�t :)
	 */
	/*
	 * void kiir (void) {
	 * 
	 */

	public void kiir() throws FileNotFoundException {
		// Sokkal eleg�nsabb lenne (�s m�s, a bevezet�sben nem kibontand� reentr�ns
		// k�rd�sek miatt is, mert
		// ugye ha most k�t helyr�l h�vj�k meg az objektum ilyen f�ggv�nyeit, tah�t ha
		// k�tszer kezd futni az
		// objektum kiir() fgv.-e pl., az komoly hiba, mert elromlana a m�lys�g... teh�t
		// a mostani megold�sunk
		// nem reentr�ns) ha nem haszn�ln�nk a C verzi�ban glob�lis v�ltoz�kat, a C++
		// v�ltozatban p�ld�nytagot a
		// m�lys�g kezel�s�re: http://progpater.blog.hu/2011/03/05/there_is_no_spoon
		melyseg = 0;
		// ha nem mondta meg a h�v� az �zenetben, hogy hova �rjuk ki a f�t, akkor a
		// sztenderd out-ra nyomjuk
		/*
		 * kiir (&gyoker, std::cout);
		 *
		 */
		kiir(gyoker, new java.io.PrintWriter(System.out));

	}
	/*
	 * m�r nem haszn�ljuk, tartalm�t a dtor h�vja void szabadit (void) { szabadit
	 * (gyoker.egyesGyermek ()); szabadit (gyoker.nullasGyermek ()); // mag�t a
	 * gy�keret nem szabad�tjuk, hiszen azt nem mi foglaltuk a szabad t�rban
	 * (halmon). }
	 */

	/*
	 * A v�ltozatoss�g kedv��rt ezeket az oszt�lydefin�ci� (class LZWBinFa {...};)
	 * ut�n defini�ljuk, hogy k�nytelen l�gy az LZWBinFa �s a :: hat�k�r oper�torral
	 * min�s�tve defini�lni :) l. lentebb
	 */
	/*
	 * int getMelyseg (void); double getAtlag (void); double getSzoras (void);
	 */
	/*
	 * V�gyunk, hogy a fel�p�tett LZW f�t ki tudjuk nyomni ilyenform�n: std::cout <<
	 * binFa; de mivel a << oper�tor is a sztenderd n�vt�rben van, de a using
	 * namespace std-t elvb�l nem haszn�ljuk bevezet� kurzusban, �gy ez a
	 * konstrukci� csak az argf�gg� n�vfelold�s miatt fordul le (B&L k�nyv 185. o.
	 * teteje) �m itt nem az a l�nyeg, hanem, hogy a cout ostream oszt�lybeli, �gy
	 * abban az oszt�lyban k�ne m�dos�tani, hogy tudjon ki�rni LZWBinFa
	 * oszt�lybelieket... e helyett a glob�lis << oper�tort terhelj�k t�l,
	 * 
	 * a kiFile << binFa azt jelenti, hogy
	 * 
	 * - tagf�ggv�nyk�nt: kiFile.operator<<(binFa) de ehhez a kiFile valamilyen
	 * std::ostream stream oszt�ly forr�s�ba kellene bele�rni ezt a tagf�ggv�nyt,
	 * amely ismeri a mi LZW binf�nkat...
	 * 
	 * - glob�lis f�ggv�nyk�nt: operator<<(kiFile, binFa) �s pont ez l�tszik a
	 * k�vetkez� sorban:
	 * 
	 */
	/*
	 * friend std::ostream & operator<< (std::ostream & os, LZWBinFa & bf) { bf.kiir
	 * (os); return os; } void kiir (std::ostream & os) { melyseg = 0; kiir
	 * (&gyoker, os); }
	 * 
	 */
	public void kiir(java.io.PrintWriter os) throws FileNotFoundException {
		melyseg = 0;
		kiir(gyoker, os);
	}

	/*
	 * private: class Csomopont { public:
	 * 
	 */
	class Csomopont {

		/*
		 * A param�ter n�lk�li konstruktor az elep�rtelmezett '/' "gy�k�r-bet�vel" hozza
		 * l�tre a csom�pontot, ilyet h�vunk a f�b�l, aki tagk�nt tartalmazza a
		 * gy�keret. M�sk�l�nben, ha valami bet�vel h�vjuk, akkor azt teszi a "betu"
		 * tagba, a k�t gyermekre mutat� mutat�t pedig nullra �ll�tjuk, C++-ban a 0 is
		 * megteszi.
		 */
		/*
		 * Csomopont (char b = '/'):betu (b), balNulla (0), jobbEgy (0) { }; ~Csomopont
		 * () { };
		 *
		 */
		public Csomopont(char betu) {
			this.betu = betu;
			balNulla = null;
			jobbEgy = null;
		}

		;

		// Aktu�lis csom�pont, mondd meg n�kem, ki a bal oldali gyermeked
		// (a C verzi� logik�j�val m�xik ez is: ha nincs, akkor a null megy vissza)
		/*
		 * Csomopont *nullasGyermek () const { return balNulla; }
		 */
		public Csomopont nullasGyermek() {
			return balNulla;
		}
		// Aktu�lis csom�pon,t mondd meg n�kem, ki a jobb oldali gyermeked?
		/*
		 * Csomopont *egyesGyermek () const { return jobbEgy; }
		 */

		public Csomopont egyesGyermek() {
			return jobbEgy;
		}
		// Aktu�lis csom�pont, �mhol legyen a "gy" mutatta csom�pont a bal oldali
		// gyereked!
		/*
		 * void ujNullasGyermek (Csomopont * gy) { balNulla = gy; }
		 */

		public void ujNullasGyermek(Csomopont gy) {
			balNulla = gy;
		}
		// Aktu�lis csom�pont, �mhol legyen a "gy" mutatta csom�pont a jobb oldali
		// gyereked!
		/*
		 * void ujEgyesGyermek (Csomopont * gy) { jobbEgy = gy; }
		 */

		public void ujEgyesGyermek(Csomopont gy) {
			jobbEgy = gy;
		}
		// Aktu�lis csom�pont: Te milyen bet�t hordozol?
		// (a const kulcssz�val jelezz�k, hogy nem b�ntjuk a p�ld�nyt)
		/*
		 * char getBetu () const { return betu; }
		 */

		public char getBetu() {
			return betu;
		}

		/*
		 * private:
		 */
		// friend class LZWBinFa; /* mert ebben a valtozatban az LZWBinFa met�dusai nem
		// k�zvetlen�l
		// a Csomopont tagjaival dolgoznak, hanem be�ll�t�/lek�rdez� �zenetekkel �rik el
		// azokat */
		// Milyen bet�t hordoz a csom�pont
		private char betu;
		// Melyik m�sik csom�pont a bal oldali gyermeke? (a C v�ltozatb�l "�r�k�lt"
		// logika:
		// ha hincs ilyen csermek, akkor balNulla == null) igaz
		/*
		 * Csomopont *balNulla; Csomopont *jobbEgy;
		 *
		 */
		private Csomopont balNulla = null;
		private Csomopont jobbEgy = null;
		// nem m�solhat� a csom�pont (�k�rszab�ly: ha van valamilye a szabad t�rban,
		// letiltjuk a m�sol� konstruktort, meg a m�sol� �rt�kad�st)
		/*
		 * Csomopont (const Csomopont &); Csomopont & operator= (const Csomopont &);
		 *
		 */
	};

	/*
	 * Mindig a fa "LZW algoritmus logik�ja szerinti aktu�lis" csom�pontj�ra mutat
	 */
	/*
	 * Csomopont *fa;
	 *
	 */
	private Csomopont fa = null;
	// technikai
	private int melyseg, atlagosszeg, atlagdb;
	private double szorasosszeg;
	// szok�sosan: nocopyable
	/*
	 * LZWBinFa (const LZWBinFa &); LZWBinFa & operator= (const LZWBinFa &);
	 * 
	 */

	/*
	 * Ki�rja a csom�pontot az os csatorn�ra. A rekurzi� kapcs�n l�sd a kor�bbi
	 * K&R-es utal�st...
	 */
	/*
	 * void kiir (Csomopont * elem, std::ostream & os) {
	 * 
	 */
	public void kiir(Csomopont elem, java.io.PrintWriter os) throws FileNotFoundException {
		// Nem l�tez� csom�ponttal nem foglalkozunk... azaz ez a rekurzi� le�ll�t�sa
		/*
		 * if (elem != NULL) { ++melyseg; kiir (elem->egyesGyermek (), os); // ez a
		 * postorder bej�r�shoz k�pest // 1-el nagyobb m�lys�g, ez�rt -1 for (int i = 0;
		 * i < melyseg; ++i) os << "---"; os << elem->getBetu () << "(" << melyseg - 1
		 * << ")" << std::endl; kiir (elem->nullasGyermek (), os); --melyseg; }
		 *
		 */
		if (elem != null) {
			++melyseg;
			kiir(elem.egyesGyermek(), os);
			// ez a postorder bej�r�shoz k�pest
			// 1-el nagyobb m�lys�g, ez�rt -1
			for (int i = 0; i < melyseg; ++i) {
				os.print("---");
			}
			os.print(elem.getBetu());
			os.print("(");
			os.print(melyseg - 1);
			os.println(")");
			kiir(elem.nullasGyermek(), os);
			--melyseg;
		}
	}
	/*
	 * void szabadit (Csomopont * elem) { // Nem l�tez� csom�ponttal nem
	 * foglalkozunk... azaz ez a rekurzi� le�ll�t�sa if (elem != NULL) { szabadit
	 * (elem->egyesGyermek ()); szabadit (elem->nullasGyermek ()); // ha a csom�pont
	 * mindk�t gyermek�t felszabad�tottuk // azut�n szabad�tjuk mag�t a csom�pontot:
	 * delete elem; } }
	 */
	/*
	 * protected: // ha esetleg egyszer majd kiterjesztj�k az oszt�lyt, mert
	 */
	// akarunk benne valami �jdons�got csin�lni, vagy megl�v� tev�kenys�get
	// m�shogy... stb.
	// akkor ezek l�tszanak majd a gyerek oszt�lyban is

	/*
	 * A f�ban tagk�nt benne van egy csom�pont, ez er�sen ki van t�ntetve, � a
	 * gy�k�r:
	 */
	/*
	 * Csomopont gyoker;
	 *
	 */
	protected Csomopont gyoker = new Csomopont('/');
	int maxMelyseg;
	double atlag, szoras;

	/*
	 * void rmelyseg (Csomopont * elem); void ratlag (Csomopont * elem); void
	 * rszoras (Csomopont * elem);
	 */
	/*
	 * };
	 */
	// N�h�ny f�ggv�nyt az oszt�lydefin�ci� ut�n defini�lunk, hogy l�ssunk ilyet is
	// ... :)
	// Nem er�ltetj�k viszont a k�l�n f�jlba szed�st, mert a sablonoszt�lyos�tott
	// tov�bb
	// fejleszt�sben az linkel�si gondot okozna, de ez a t�ma m�r kivezet a
	// laborteljes�t�s
	// sz�ks�ges feladat�b�l:
	// http://progpater.blog.hu/2011/04/12/imadni_fogjak_a_c_t_egy_emberkent_tiszta_szivbol_3
	// Egy�bk�nt a melyseg, atlag �s szoras fgv.-ek a kiir fgv.-el teljesen egy
	// kaptafa.
	/*
	 * int LZWBinFa::getMelyseg (void) { melyseg = maxMelyseg = 0; rmelyseg
	 * (&gyoker); return maxMelyseg - 1; }
	 * 
	 * double LZWBinFa::getAtlag (void) { melyseg = atlagosszeg = atlagdb = 0;
	 * ratlag (&gyoker); atlag = ((double) atlagosszeg) / atlagdb; return atlag; }
	 * 
	 * double LZWBinFa::getSzoras (void) { atlag = getAtlag (); szorasosszeg = 0.0;
	 * melyseg = atlagdb = 0;
	 * 
	 * rszoras (&gyoker);
	 * 
	 * if (atlagdb - 1 > 0) szoras = std::sqrt (szorasosszeg / (atlagdb - 1)); else
	 * szoras = std::sqrt (szorasosszeg);
	 * 
	 * return szoras; }
	 * 
	 * void LZWBinFa::rmelyseg (Csomopont * elem) { if (elem != NULL) { ++melyseg;
	 * if (melyseg > maxMelyseg) maxMelyseg = melyseg; rmelyseg (elem->egyesGyermek
	 * ()); // ez a postorder bej�r�shoz k�pest // 1-el nagyobb m�lys�g, ez�rt -1
	 * rmelyseg (elem->nullasGyermek ()); --melyseg; } }
	 * 
	 * void LZWBinFa::ratlag (Csomopont * elem) { if (elem != NULL) { ++melyseg;
	 * ratlag (elem->egyesGyermek ()); ratlag (elem->nullasGyermek ()); --melyseg;
	 * if (elem->egyesGyermek () == NULL && elem->nullasGyermek () == NULL) {
	 * ++atlagdb; atlagosszeg += melyseg; } } }
	 * 
	 * void LZWBinFa::rszoras (Csomopont * elem) { if (elem != NULL) { ++melyseg;
	 * rszoras (elem->egyesGyermek ()); rszoras (elem->nullasGyermek ()); --melyseg;
	 * if (elem->egyesGyermek () == NULL && elem->nullasGyermek () == NULL) {
	 * ++atlagdb; szorasosszeg += ((melyseg - atlag) * (melyseg - atlag)); } } }
	 */
	public int getMelyseg() {
		melyseg = maxMelyseg = 0;
		rmelyseg(gyoker);
		return maxMelyseg - 1;
	}

	public double getAtlag() {
		melyseg = atlagosszeg = atlagdb = 0;
		ratlag(gyoker);
		atlag = ((double) atlagosszeg) / atlagdb;
		return atlag;
	}

	public double getSzoras() {
		atlag = getAtlag();
		szorasosszeg = 0.0;
		melyseg = atlagdb = 0;

		rszoras(gyoker);

		if (atlagdb - 1 > 0) {
			szoras = Math.sqrt(szorasosszeg / (atlagdb - 1));
		} else {
			szoras = Math.sqrt(szorasosszeg);
		}

		return szoras;
	}

	public void rmelyseg(Csomopont elem) {
		if (elem != null) {
			++melyseg;
			if (melyseg > maxMelyseg) {
				maxMelyseg = melyseg;
			}
			rmelyseg(elem.egyesGyermek());
			// ez a postorder bej�r�shoz k�pest
			// 1-el nagyobb m�lys�g, ez�rt -1
			rmelyseg(elem.nullasGyermek());
			--melyseg;
		}
	}

	public void ratlag(Csomopont elem) {
		if (elem != null) {
			++melyseg;
			ratlag(elem.egyesGyermek());
			ratlag(elem.nullasGyermek());
			--melyseg;
			if (elem.egyesGyermek() == null && elem.nullasGyermek() == null) {
				++atlagdb;
				atlagosszeg += melyseg;
			}
		}
	}

	public void rszoras(Csomopont elem) {
		if (elem != null) {
			++melyseg;
			rszoras(elem.egyesGyermek());
			rszoras(elem.nullasGyermek());
			--melyseg;
			if (elem.egyesGyermek() == null && elem.nullasGyermek() == null) {
				++atlagdb;
				szorasosszeg += ((melyseg - atlag) * (melyseg - atlag));
			}
		}
	}

	// teszt pl.:
	// http://progpater.blog.hu/2011/03/05/labormeres_otthon_avagy_hogyan_dolgozok_fel_egy_pedat
	// [norbi@sgu ~]$ echo "01111001001001000111"|./z3a2
	// ------------1(3)
	// ---------1(2)
	// ------1(1)
	// ---------0(2)
	// ------------0(3)
	// ---------------0(4)
	// ---/(0)
	// ---------1(2)
	// ------0(1)
	// ---------0(2)
	// depth = 4
	// mean = 2.75
	// var = 0.957427
	// a laborv�d�shez majd ezt a tesztel�st haszn�ljuk:
	// http://

	/*
	 * Ez volt eddig a main, de most komplexebb kell, mert explicite bej�v�, kimen�
	 * f�jlokkal kell dolgozni int main () { char b; LZWBinFa binFa;
	 * 
	 * while (std::cin >> b) { binFa << b; }
	 * 
	 * //std::cout << binFa.kiir (); // �gy rajzolt ki a f�t a kor�bbi verzi�kban
	 * de, hogy izgalmasabb legyen // a p�lda, azaz ki lehessen tolni az LZWBinFa-t
	 * kimeneti csatorn�ra:
	 * 
	 * std::cout << binFa; // ehhez kell a glob�lis operator<< t�lterhel�se, l�sd
	 * fentebb
	 * 
	 * std::cout << "depth = " << binFa.getMelyseg () << std::endl; std::cout <<
	 * "mean = " << binFa.getAtlag () << std::endl; std::cout << "var = " <<
	 * binFa.getSzoras () << std::endl;
	 * 
	 * binFa.szabadit ();
	 * 
	 * return 0; }
	 */

	/*
	 * A parancssor arg. kezel�st egyszer�en bedolgozzuk a 2. hull�m kapcsol�d�
	 * feladat�b�l:
	 * http://progpater.blog.hu/2011/03/12/hey_mikey_he_likes_it_ready_for_more_3 de
	 * mivel nek�nk sokkal egyszer�bb is el�g, alig hagyunk meg bel�le valamit...
	 */
	/*
	 * void usage (void) { std::cout << "Usage: lzwtree in_file -o out_file" <<
	 * std::endl; }
	 */
	public static void usage() {
		System.out.println("Usage: lzwtree in_file -o out_file");
	}

	/*
	 * int main (int argc, char *argv[]) {
	 */
	public static void main(String args[]) {
		// http://progpater.blog.hu/2011/03/12/hey_mikey_he_likes_it_ready_for_more_3
		// alapj�n a parancssor argok ottani eleg�ns feldolgoz�s�b�l kb. ennyi marad:
		// "*((*++argv)+1)"...

		// a ki�r�s szerint ./lzwtree in_file -o out_file alakra kell mennie, ez 4 db
		// arg:
		/*
		 * if (argc != 4) { // ha nem annyit kapott a program, akkor felhom�lyos�tjuk
		 * err�l a j�zetr: usage (); // �s jelezz�k az oper�ci�s rendszer fel�, hogy
		 * valami g�z volt... return -1; }
		 */
		if (args.length != 3) {
			// ha nem annyit kapott a program, akkor felhom�lyos�tjuk err�l a j�zetr:
			usage();
			// �s jelezz�k az oper�ci�s rendszer fel�, hogy valami g�z volt...
			System.exit(-1);
		}
		// "Megjegyezz�k" a bemen� f�jl nev�t
		/*
		 * char *inFile = *++argv;
		 */
		String inFile = args[0];
		// a -o kapcsol� j�n?
		/*
		 * if (*((*++argv) + 1) != 'o') { usage (); return -2; }
		 *
		 */
		if (!"-o".equals(args[1])) {
			usage();
			System.exit(-1);
		}

		// ha igen, akkor az 5. el�ad�sb�l kim�soljuk a f�jlkezel�s C++ v�ltozat�t:
		/*
		 * std::fstream beFile (inFile, std::ios_base::in);
		 */
		try {

			java.io.FileInputStream beFile = new java.io.FileInputStream(new java.io.File(args[0]));
			// fejlesztgetj�k a forr�st:
			// http://progpater.blog.hu/2011/04/17/a_tizedik_tizenegyedik_labor
			/*
			 * if (!beFile) { std::cout << inFile << " nem letezik..." << std::endl; usage
			 * (); return -3; }
			 */ /*
				 * std::fstream kiFile (*++argv, std::ios_base::out);
				 */
			java.io.PrintWriter kiFile = new java.io.PrintWriter(
					new java.io.BufferedWriter(new java.io.FileWriter(args[2])));

			/*
			 * unsigned char b; // ide olvassik majd a bej�v� f�jl b�jtjait
			 *
			 */
			byte[] b = new byte[1];
			/*
			 * LZWBinFa binFa; // s nyomjuk majd be az LZW fa objektumunkba
			 *
			 */
			LZWBinFa binFa = new LZWBinFa();

			// a bemenetet bin�risan olvassuk, de a kimen� f�jlt m�r karakteresen �rjuk,
			// hogy meg tudjuk
			// majd n�zni... :) l. az eml�tett 5. ea. C -> C++ gy�kkettes �t�r�si p�ld�it
			/*
			 * while (beFile.read ((char *) &b, sizeof (unsigned char))) if (b == 0x0a)
			 * break;
			 */
			while (beFile.read(b) != -1) {
				if (b[0] == 0x0a) {
					break;
				}
			}
			/*
			 * bool kommentben = false;
			 *
			 */
			boolean kommentben = false;

			/*
			 * while (beFile.read ((char *) &b, sizeof (unsigned char))) {
			 *
			 * if (b == 0x3e) { // > karakter kommentben = true; continue; }
			 *
			 * if (b == 0x0a) { // �jsor kommentben = false; continue; }
			 *
			 * if (kommentben) continue;
			 *
			 * if (b == 0x4e) // N bet� continue;
			 *
			 * // egyszer�en a kor�bbi d.c k�dj�t bem�soljuk // laboron t�bbsz�r lerajzoltuk
			 * ezt a bit-tologat�st: // a b-ben l�v� b�jt bitjeit egyenk�nt megn�zz�k for
			 * (int i = 0; i < 8; ++i) { // maszkolunk eddig..., most m�r sim�n �rjuk az if
			 * fej�be a legmagasabb helyi�rt�k� bit vizsg�lat�t // csupa 0 lesz benne a
			 * v�g�n pedig a vizsg�lt 0 vagy 1, az if megmondja melyik: if (b & 0x80) // ha
			 * a vizsg�lt bit 1, akkor az '1' bet�t nyomjuk az LZW fa objektumunkba binFa <<
			 * '1'; else // k�l�nben meg a '0' bet�t: binFa << '0'; b <<= 1; }
			 *
			 * }
			 */
			while (beFile.read(b) != -1) {

				if (b[0] == 0x3e) { // > karakter
					kommentben = true;
					continue;
				}

				if (b[0] == 0x0a) { // �jsor
					kommentben = false;
					continue;
				}

				if (kommentben) {
					continue;
				}

				if (b[0] == 0x4e) // N bet�
				{
					continue;
				}

				// egyszer�en a kor�bbi d.c k�dj�t bem�soljuk
				// laboron t�bbsz�r lerajzoltuk ezt a bit-tologat�st:
				// a b-ben l�v� b�jt bitjeit egyenk�nt megn�zz�k
				for (int i = 0; i < 8; ++i) {
					// maszkolunk eddig..., most m�r sim�n �rjuk az if fej�be a legmagasabb
					// helyi�rt�k� bit vizsg�lat�t
					// csupa 0 lesz benne a v�g�n pedig a vizsg�lt 0 vagy 1, az if megmondja melyik:
					if ((b[0] & 0x80) != 0) // ha a vizsg�lt bit 1, akkor az '1' bet�t nyomjuk az LZW fa objektumunkba
					{
						binFa.egyBitFeldolg('1');
					} else // k�l�nben meg a '0' bet�t:
					{
						binFa.egyBitFeldolg('0');
					}
					b[0] <<= 1;
				}

			}

			// std::cout << binFa.kiir (); // �gy rajzolt ki a f�t a kor�bbi verzi�kban de,
			// hogy izgalmasabb legyen
			// a p�lda, azaz ki lehessen tolni az LZWBinFa-t kimeneti csatorn�ra:
			/*
			 * kiFile << binFa; // ehhez kell a glob�lis operator<< t�lterhel�se, l�sd
			 * fentebb // (j� ez az OO, mert mi ugye nem igaz�n erre gondoltunk, amikor
			 * �rtuk, m�gis megy, hurr�)
			 */
			binFa.kiir(kiFile);

			/*
			 * kiFile << "depth = " << binFa.getMelyseg () << std::endl; kiFile << "mean = "
			 * << binFa.getAtlag () << std::endl; kiFile << "var = " << binFa.getSzoras ()
			 * << std::endl;
			 */
			kiFile.println("depth = " + binFa.getMelyseg());
			kiFile.println("mean = " + binFa.getAtlag());
			kiFile.println("var = " + binFa.getSzoras());

			kiFile.close();
			beFile.close();

		} catch (java.io.FileNotFoundException fnfException) {
			fnfException.printStackTrace();
		} catch (java.io.IOException ioException) {
			ioException.printStackTrace();
		}

	}
}
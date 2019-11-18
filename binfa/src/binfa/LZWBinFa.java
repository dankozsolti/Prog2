package binfa;

import java.io.FileNotFoundException;

public class LZWBinFa {
	/*
	 * public:
	 *
	 */
	/*
	 * Szemben a bináris keresõfánkkal (BinFa osztály)
	 * http://progpater.blog.hu/2011/04/12/
	 * imadni_fogjak_a_c_t_egy_emberkent_tiszta_szivbol_3 itt (LZWBinFa osztály) a
	 * fa gyökere nem pointer, hanem a '/' betüt tartalmazó objektum, lásd majd a
	 * védett tagok között lent: Csomopont gyoker; A fa viszont már pointer, mindig
	 * az épülõ LZW-fánk azon csomópontjára mutat, amit az input feldolgozása során
	 * az LZW algoritmus logikája diktál:
	 * http://progpater.blog.hu/2011/02/19/gyonyor_a_tomor Ez a konstruktor annyit
	 * csinál, hogy a fa mutatót ráállítja a gyökérre. (Mert ugye laboron, blogon,
	 * elõadásban tisztáztuk, hogy a tartalmazott tagok, most "Csomopont gyoker"
	 * konstruktora elõbb lefut, mint a tagot tartalmazó LZWBinFa osztály
	 * konstruktora, éppen a következõ, azaz a fa=&gyoker OK.)
	 */
	/*
	 * LZWBinFa ():fa (&gyoker) { } ~LZWBinFa () { szabadit (gyoker.egyesGyermek
	 * ()); szabadit (gyoker.nullasGyermek ()); }
	 */

	public LZWBinFa() {

		fa = gyoker;

	}
	/*
	 * Tagfüggvényként túlterheljük a << operátort, ezzel a célunk, hogy felkeltsük
	 * a hallgató érdeklõdését, mert ekkor így nyomhatjuk a fába az inputot: binFa
	 * << b; ahol a b egy '0' vagy '1'-es betû. Mivel tagfüggvény, így van rá
	 * "értelmezve" az aktuális (this "rejtett paraméterként" kapott ) példány, azaz
	 * annak a fának amibe éppen be akarjuk nyomni a b betût a tagjai (pl.: "fa",
	 * "gyoker") használhatóak a függvényben.
	 * 
	 * A függvénybe programoztuk az LZW fa építésének algoritmusát tk.:
	 * http://progpater.blog.hu/2011/02/19/gyonyor_a_tomor
	 * 
	 * a b formális param az a betû, amit éppen be kell nyomni a fába.
	 * 
	 * a binFa << b (ahol a b majd a végén látszik, hogy már az '1' vagy a '0') azt
	 * jelenti tagfüggvényként, hogy binFa.operator<<(b) (globálisként így festene:
	 * operator<<(binFa, b) )
	 * 
	 */
	/*
	 * void operator<< (char b) { // Mit kell betenni éppen, '0'-t? if (b == '0') {
	 * 
	 */

	public void egyBitFeldolg(char b) {
		// Mit kell betenni éppen, '0'-t?
		if (b == '0') {
			/*
			 * Van '0'-s gyermeke az aktuális csomópontnak? megkérdezzük Tõle, a "fa" mutató
			 * éppen reá mutat
			 */
			/*
			 * if (!fa->nullasGyermek ()) // ha nincs, hát akkor csinálunk { // elkészítjük,
			 * azaz páldányosítunk a '0' betû akt. parammal Csomopont *uj = new Csomopont
			 * ('0'); // az aktuális csomópontnak, ahol állunk azt üzenjük, hogy // jegyezze
			 * már be magának, hogy nullás gyereke mostantól van // küldjük is Neki a gyerek
			 * címét: fa->ujNullasGyermek (uj); // és visszaállunk a gyökérre (mert ezt
			 * diktálja az alg.) fa = &gyoker; } else // ha van, arra rálépünk { // azaz a
			 * "fa" pointer már majd a szóban forgó gyermekre mutat: fa = fa->nullasGyermek
			 * (); } }
			 *
			 */
			if (fa.nullasGyermek() == null) // ha nincs, hát akkor csinálunk
			{
				// elkészítjük, azaz páldányosítunk a '0' betû akt. parammal
				Csomopont uj = new Csomopont('0');
				// az aktuális csomópontnak, ahol állunk azt üzenjük, hogy
				// jegyezze már be magának, hogy nullás gyereke mostantól van
				// küldjük is Neki a gyerek címét:
				fa.ujNullasGyermek(uj);
				// és visszaállunk a gyökérre (mert ezt diktálja az alg.)
				fa = gyoker;
			} else // ha van, arra rálépünk
			{
				// azaz a "fa" pointer már majd a szóban forgó gyermekre mutat:
				fa = fa.nullasGyermek();
			}
		} // Mit kell betenni éppen, vagy '1'-et?
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
	 * A bejárással kapcsolatos függvényeink (túlterhelt kiir-ók, atlag, ratlag
	 * stb.) rekurzívak, tk. a rekurzív fabejárást valósítják meg (lásd a 3. elõadás
	 * "Fabejárás" c. fóliáját és társait)
	 * 
	 * (Ha a rekurzív függvénnyel általában gondod van => K&R könyv megfelelõ része:
	 * a 3. ea. izometrikus részében ezt "letáncoltuk" :) és külön idéztük a K&R
	 * álláspontját :)
	 */
	/*
	 * void kiir (void) {
	 * 
	 */

	public void kiir() throws FileNotFoundException {
		// Sokkal elegánsabb lenne (és más, a bevezetésben nem kibontandó reentráns
		// kérdések miatt is, mert
		// ugye ha most két helyrõl hívják meg az objektum ilyen függvényeit, tahát ha
		// kétszer kezd futni az
		// objektum kiir() fgv.-e pl., az komoly hiba, mert elromlana a mélység... tehát
		// a mostani megoldásunk
		// nem reentráns) ha nem használnánk a C verzióban globális változókat, a C++
		// változatban példánytagot a
		// mélység kezelésére: http://progpater.blog.hu/2011/03/05/there_is_no_spoon
		melyseg = 0;
		// ha nem mondta meg a hívó az üzenetben, hogy hova írjuk ki a fát, akkor a
		// sztenderd out-ra nyomjuk
		/*
		 * kiir (&gyoker, std::cout);
		 *
		 */
		kiir(gyoker, new java.io.PrintWriter(System.out));

	}
	/*
	 * már nem használjuk, tartalmát a dtor hívja void szabadit (void) { szabadit
	 * (gyoker.egyesGyermek ()); szabadit (gyoker.nullasGyermek ()); // magát a
	 * gyökeret nem szabadítjuk, hiszen azt nem mi foglaltuk a szabad tárban
	 * (halmon). }
	 */

	/*
	 * A változatosság kedvéért ezeket az osztálydefiníció (class LZWBinFa {...};)
	 * után definiáljuk, hogy kénytelen légy az LZWBinFa és a :: hatókör operátorral
	 * minõsítve definiálni :) l. lentebb
	 */
	/*
	 * int getMelyseg (void); double getAtlag (void); double getSzoras (void);
	 */
	/*
	 * Vágyunk, hogy a felépített LZW fát ki tudjuk nyomni ilyenformán: std::cout <<
	 * binFa; de mivel a << operátor is a sztenderd névtérben van, de a using
	 * namespace std-t elvbõl nem használjuk bevezetõ kurzusban, így ez a
	 * konstrukció csak az argfüggõ névfeloldás miatt fordul le (B&L könyv 185. o.
	 * teteje) ám itt nem az a lényeg, hanem, hogy a cout ostream osztálybeli, így
	 * abban az osztályban kéne módosítani, hogy tudjon kiírni LZWBinFa
	 * osztálybelieket... e helyett a globális << operátort terheljük túl,
	 * 
	 * a kiFile << binFa azt jelenti, hogy
	 * 
	 * - tagfüggvényként: kiFile.operator<<(binFa) de ehhez a kiFile valamilyen
	 * std::ostream stream osztály forrásába kellene beleírni ezt a tagfüggvényt,
	 * amely ismeri a mi LZW binfánkat...
	 * 
	 * - globális függvényként: operator<<(kiFile, binFa) és pont ez látszik a
	 * következõ sorban:
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
		 * A paraméter nélküli konstruktor az elepértelmezett '/' "gyökér-betûvel" hozza
		 * létre a csomópontot, ilyet hívunk a fából, aki tagként tartalmazza a
		 * gyökeret. Máskülönben, ha valami betûvel hívjuk, akkor azt teszi a "betu"
		 * tagba, a két gyermekre mutató mutatót pedig nullra állítjuk, C++-ban a 0 is
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

		// Aktuális csomópont, mondd meg nékem, ki a bal oldali gyermeked
		// (a C verzió logikájával mûxik ez is: ha nincs, akkor a null megy vissza)
		/*
		 * Csomopont *nullasGyermek () const { return balNulla; }
		 */
		public Csomopont nullasGyermek() {
			return balNulla;
		}
		// Aktuális csomópon,t mondd meg nékem, ki a jobb oldali gyermeked?
		/*
		 * Csomopont *egyesGyermek () const { return jobbEgy; }
		 */

		public Csomopont egyesGyermek() {
			return jobbEgy;
		}
		// Aktuális csomópont, ímhol legyen a "gy" mutatta csomópont a bal oldali
		// gyereked!
		/*
		 * void ujNullasGyermek (Csomopont * gy) { balNulla = gy; }
		 */

		public void ujNullasGyermek(Csomopont gy) {
			balNulla = gy;
		}
		// Aktuális csomópont, ímhol legyen a "gy" mutatta csomópont a jobb oldali
		// gyereked!
		/*
		 * void ujEgyesGyermek (Csomopont * gy) { jobbEgy = gy; }
		 */

		public void ujEgyesGyermek(Csomopont gy) {
			jobbEgy = gy;
		}
		// Aktuális csomópont: Te milyen betût hordozol?
		// (a const kulcsszóval jelezzük, hogy nem bántjuk a példányt)
		/*
		 * char getBetu () const { return betu; }
		 */

		public char getBetu() {
			return betu;
		}

		/*
		 * private:
		 */
		// friend class LZWBinFa; /* mert ebben a valtozatban az LZWBinFa metódusai nem
		// közvetlenül
		// a Csomopont tagjaival dolgoznak, hanem beállító/lekérdezõ üzenetekkel érik el
		// azokat */
		// Milyen betût hordoz a csomópont
		private char betu;
		// Melyik másik csomópont a bal oldali gyermeke? (a C változatból "örökölt"
		// logika:
		// ha hincs ilyen csermek, akkor balNulla == null) igaz
		/*
		 * Csomopont *balNulla; Csomopont *jobbEgy;
		 *
		 */
		private Csomopont balNulla = null;
		private Csomopont jobbEgy = null;
		// nem másolható a csomópont (ökörszabály: ha van valamilye a szabad tárban,
		// letiltjuk a másoló konstruktort, meg a másoló értékadást)
		/*
		 * Csomopont (const Csomopont &); Csomopont & operator= (const Csomopont &);
		 *
		 */
	};

	/*
	 * Mindig a fa "LZW algoritmus logikája szerinti aktuális" csomópontjára mutat
	 */
	/*
	 * Csomopont *fa;
	 *
	 */
	private Csomopont fa = null;
	// technikai
	private int melyseg, atlagosszeg, atlagdb;
	private double szorasosszeg;
	// szokásosan: nocopyable
	/*
	 * LZWBinFa (const LZWBinFa &); LZWBinFa & operator= (const LZWBinFa &);
	 * 
	 */

	/*
	 * Kiírja a csomópontot az os csatornára. A rekurzió kapcsán lásd a korábbi
	 * K&R-es utalást...
	 */
	/*
	 * void kiir (Csomopont * elem, std::ostream & os) {
	 * 
	 */
	public void kiir(Csomopont elem, java.io.PrintWriter os) throws FileNotFoundException {
		// Nem létezõ csomóponttal nem foglalkozunk... azaz ez a rekurzió leállítása
		/*
		 * if (elem != NULL) { ++melyseg; kiir (elem->egyesGyermek (), os); // ez a
		 * postorder bejáráshoz képest // 1-el nagyobb mélység, ezért -1 for (int i = 0;
		 * i < melyseg; ++i) os << "---"; os << elem->getBetu () << "(" << melyseg - 1
		 * << ")" << std::endl; kiir (elem->nullasGyermek (), os); --melyseg; }
		 *
		 */
		if (elem != null) {
			++melyseg;
			kiir(elem.egyesGyermek(), os);
			// ez a postorder bejáráshoz képest
			// 1-el nagyobb mélység, ezért -1
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
	 * void szabadit (Csomopont * elem) { // Nem létezõ csomóponttal nem
	 * foglalkozunk... azaz ez a rekurzió leállítása if (elem != NULL) { szabadit
	 * (elem->egyesGyermek ()); szabadit (elem->nullasGyermek ()); // ha a csomópont
	 * mindkét gyermekét felszabadítottuk // azután szabadítjuk magát a csomópontot:
	 * delete elem; } }
	 */
	/*
	 * protected: // ha esetleg egyszer majd kiterjesztjük az osztályt, mert
	 */
	// akarunk benne valami újdonságot csinálni, vagy meglévõ tevékenységet
	// máshogy... stb.
	// akkor ezek látszanak majd a gyerek osztályban is

	/*
	 * A fában tagként benne van egy csomópont, ez erõsen ki van tüntetve, Õ a
	 * gyökér:
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
	// Néhány függvényt az osztálydefiníció után definiálunk, hogy lássunk ilyet is
	// ... :)
	// Nem erõltetjük viszont a külön fájlba szedést, mert a sablonosztályosított
	// tovább
	// fejlesztésben az linkelési gondot okozna, de ez a téma már kivezet a
	// laborteljesítés
	// szükséges feladatából:
	// http://progpater.blog.hu/2011/04/12/imadni_fogjak_a_c_t_egy_emberkent_tiszta_szivbol_3
	// Egyébként a melyseg, atlag és szoras fgv.-ek a kiir fgv.-el teljesen egy
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
	 * ()); // ez a postorder bejáráshoz képest // 1-el nagyobb mélység, ezért -1
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
			// ez a postorder bejáráshoz képest
			// 1-el nagyobb mélység, ezért -1
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
	// a laborvédéshez majd ezt a tesztelést használjuk:
	// http://

	/*
	 * Ez volt eddig a main, de most komplexebb kell, mert explicite bejövõ, kimenõ
	 * fájlokkal kell dolgozni int main () { char b; LZWBinFa binFa;
	 * 
	 * while (std::cin >> b) { binFa << b; }
	 * 
	 * //std::cout << binFa.kiir (); // így rajzolt ki a fát a korábbi verziókban
	 * de, hogy izgalmasabb legyen // a példa, azaz ki lehessen tolni az LZWBinFa-t
	 * kimeneti csatornára:
	 * 
	 * std::cout << binFa; // ehhez kell a globális operator<< túlterhelése, lásd
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
	 * A parancssor arg. kezelést egyszerûen bedolgozzuk a 2. hullám kapcsolódó
	 * feladatából:
	 * http://progpater.blog.hu/2011/03/12/hey_mikey_he_likes_it_ready_for_more_3 de
	 * mivel nekünk sokkal egyszerûbb is elég, alig hagyunk meg belõle valamit...
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
		// alapján a parancssor argok ottani elegáns feldolgozásából kb. ennyi marad:
		// "*((*++argv)+1)"...

		// a kiírás szerint ./lzwtree in_file -o out_file alakra kell mennie, ez 4 db
		// arg:
		/*
		 * if (argc != 4) { // ha nem annyit kapott a program, akkor felhomályosítjuk
		 * errõl a júzetr: usage (); // és jelezzük az operációs rendszer felé, hogy
		 * valami gáz volt... return -1; }
		 */
		if (args.length != 3) {
			// ha nem annyit kapott a program, akkor felhomályosítjuk errõl a júzetr:
			usage();
			// és jelezzük az operációs rendszer felé, hogy valami gáz volt...
			System.exit(-1);
		}
		// "Megjegyezzük" a bemenõ fájl nevét
		/*
		 * char *inFile = *++argv;
		 */
		String inFile = args[0];
		// a -o kapcsoló jön?
		/*
		 * if (*((*++argv) + 1) != 'o') { usage (); return -2; }
		 *
		 */
		if (!"-o".equals(args[1])) {
			usage();
			System.exit(-1);
		}

		// ha igen, akkor az 5. elõadásból kimásoljuk a fájlkezelés C++ változatát:
		/*
		 * std::fstream beFile (inFile, std::ios_base::in);
		 */
		try {

			java.io.FileInputStream beFile = new java.io.FileInputStream(new java.io.File(args[0]));
			// fejlesztgetjük a forrást:
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
			 * unsigned char b; // ide olvassik majd a bejövõ fájl bájtjait
			 *
			 */
			byte[] b = new byte[1];
			/*
			 * LZWBinFa binFa; // s nyomjuk majd be az LZW fa objektumunkba
			 *
			 */
			LZWBinFa binFa = new LZWBinFa();

			// a bemenetet binárisan olvassuk, de a kimenõ fájlt már karakteresen írjuk,
			// hogy meg tudjuk
			// majd nézni... :) l. az említett 5. ea. C -> C++ gyökkettes átírási példáit
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
			 * if (b == 0x0a) { // újsor kommentben = false; continue; }
			 *
			 * if (kommentben) continue;
			 *
			 * if (b == 0x4e) // N betû continue;
			 *
			 * // egyszerûen a korábbi d.c kódját bemásoljuk // laboron többször lerajzoltuk
			 * ezt a bit-tologatást: // a b-ben lévõ bájt bitjeit egyenként megnézzük for
			 * (int i = 0; i < 8; ++i) { // maszkolunk eddig..., most már simán írjuk az if
			 * fejébe a legmagasabb helyiértékû bit vizsgálatát // csupa 0 lesz benne a
			 * végén pedig a vizsgált 0 vagy 1, az if megmondja melyik: if (b & 0x80) // ha
			 * a vizsgált bit 1, akkor az '1' betût nyomjuk az LZW fa objektumunkba binFa <<
			 * '1'; else // különben meg a '0' betût: binFa << '0'; b <<= 1; }
			 *
			 * }
			 */
			while (beFile.read(b) != -1) {

				if (b[0] == 0x3e) { // > karakter
					kommentben = true;
					continue;
				}

				if (b[0] == 0x0a) { // újsor
					kommentben = false;
					continue;
				}

				if (kommentben) {
					continue;
				}

				if (b[0] == 0x4e) // N betû
				{
					continue;
				}

				// egyszerûen a korábbi d.c kódját bemásoljuk
				// laboron többször lerajzoltuk ezt a bit-tologatást:
				// a b-ben lévõ bájt bitjeit egyenként megnézzük
				for (int i = 0; i < 8; ++i) {
					// maszkolunk eddig..., most már simán írjuk az if fejébe a legmagasabb
					// helyiértékû bit vizsgálatát
					// csupa 0 lesz benne a végén pedig a vizsgált 0 vagy 1, az if megmondja melyik:
					if ((b[0] & 0x80) != 0) // ha a vizsgált bit 1, akkor az '1' betût nyomjuk az LZW fa objektumunkba
					{
						binFa.egyBitFeldolg('1');
					} else // különben meg a '0' betût:
					{
						binFa.egyBitFeldolg('0');
					}
					b[0] <<= 1;
				}

			}

			// std::cout << binFa.kiir (); // így rajzolt ki a fát a korábbi verziókban de,
			// hogy izgalmasabb legyen
			// a példa, azaz ki lehessen tolni az LZWBinFa-t kimeneti csatornára:
			/*
			 * kiFile << binFa; // ehhez kell a globális operator<< túlterhelése, lásd
			 * fentebb // (jó ez az OO, mert mi ugye nem igazán erre gondoltunk, amikor
			 * írtuk, mégis megy, hurrá)
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
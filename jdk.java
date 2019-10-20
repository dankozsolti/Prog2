#include <iostream>
#include <sstream>
#include "boost/filesystem.hpp"
#include <boost/algorithm/string.hpp>
#include <boost/algorithm/string/predicate.hpp>

using namespace std;
using namespace boost;
using namespace boost::filesystem;

string repeat(int n, string what) {
	ostringstream os;
	for (int i = 0; i < n; i++)
		os << what;
	return os.str();
}

int main()
{
	string pa = "C:\\java src";
	path apk_path(pa);
	long long ennyimVan = 0;
	recursive_directory_iterator end;
	for (recursive_directory_iterator i(apk_path); i != end; ++i)
	{
		const path cp = (*i);
		string name = cp.string();
		vector<string> strs;
		split(strs, name, is_any_of("\\"));
		string fName = strs[strs.size() - 1];

		if (fName.compare("module-info.java") == -1 && fName.compare("package-info.java") == -1) {
			if (ends_with(fName, "java")) {
				cout << " " << repeat(strs.size() - 7, "+") << " " << fName.substr(0, fName.size() - 5) << endl;
				++ennyimVan;
			}
			else
			{
				cout << " | " << fName << " >" << endl;
			}
		}
	}

	cout << "JDK -> Ennyi osztalyom van: " << ennyimVan << endl;
}

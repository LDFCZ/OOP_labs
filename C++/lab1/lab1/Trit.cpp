#include "Trit.h"

namespace TernaryLogic {
	Trit operator ~ (Trit tr1) {
		if (tr1 == TRUE) return FALSE;
		else if (tr1 == FALSE) return TRUE;
		else return UNKNOWN;
	}

	Trit operator & (Trit tr1, Trit tr2) {
		if (tr1 == FALSE || tr2 == FALSE) return FALSE;
		else if (tr1 == UNKNOWN || tr2 == UNKNOWN) return UNKNOWN;
		else return  TRUE;
	}

	Trit operator | (Trit tr1, Trit tr2) {
		if (tr1 == TRUE || tr2 == TRUE) return TRUE;
		else if (tr1 == UNKNOWN || tr2 == UNKNOWN) return UNKNOWN;
		else return FALSE;
	}
}

#pragma once

namespace TernaryLogic {
	enum Trit { FALSE = 1, UNKNOWN = 0, TRUE = 2, };

	Trit operator ~ (Trit tr1);
	Trit operator & (Trit tr1, Trit tr2);
	Trit operator | (Trit tr1, Trit tr2);
}

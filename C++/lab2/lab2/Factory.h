#pragma once

#include "Worker.h"

namespace Interfaces {

	class Factory {
	public:
		virtual std::map<int, Interfaces::Worker*> produce_workres(std::vector<std::vector<std::wstring>>&) = 0;
	};
}
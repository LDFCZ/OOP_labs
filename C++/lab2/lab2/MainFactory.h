#pragma once

#include <map>

#include "Factory.h"
#include "Creator.h"

namespace Factory {

	class MainFactory : public Interfaces::Factory {
	private:
		std::map<std::wstring, Interfaces::Creator*>& _creator_container;

		MainContext::Context& _context;

		void _clear_map(std::map<int, Interfaces::Worker*>&);
	public:

		MainFactory(std::map<std::wstring, Interfaces::Creator*>&, MainContext::Context&);

		std::map<int, Interfaces::Worker*> produce_workres(std::vector<std::vector<std::wstring>>&) override;
	};
}

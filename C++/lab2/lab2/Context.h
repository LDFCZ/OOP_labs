#pragma once

#include "Worker.h"
#include "CONSTSPACE.h"
#include "IncompatibleWorkersTypes.h"

namespace MainContext {

	class Context {
	private:
		Interfaces::Types _prev_worker_type;
		std::wstring _text;
	public:

		Context();

		std::wstring get_text(Interfaces::Worker&) const;

		void set_text(Interfaces::Worker&, std::wstring&);
	};
}


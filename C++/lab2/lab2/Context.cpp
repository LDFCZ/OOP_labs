#include "Context.h"

namespace MainContext {

	Context::Context() {
		_prev_worker_type = Interfaces::Types::EMPTY;
	}

	std::wstring Context::get_text(Interfaces::Worker& worker) const {

		if (worker.get_input_type() != _prev_worker_type)
			throw ErrorsSpace::IncompatibleWorkersTypes(CONSTSPACE::INCOMPSTIBLE_WORKERS_WORKFLOW_ERROR.c_str());
		else
			return _text;
	}

	void Context::set_text(Interfaces::Worker& worker, std::wstring& text) {
		_text = text;
		_prev_worker_type = worker.get_output_type();
	}
}
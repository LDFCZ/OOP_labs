#include "GrepCreator.h"

namespace MainWorkerCreators {

	Interfaces::Worker* GrepCreator::create_worker(std::vector<std::wstring>& args, MainContext::Context& context) const {
		return new MainWorkers::GrepWorker(args, context);
	}
}

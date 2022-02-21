#include "ReplaceCreator.h"

namespace MainWorkerCreators {

	Interfaces::Worker* ReplaceCreator::create_worker(std::vector<std::wstring>& args, MainContext::Context& context) const {
		return new MainWorkers::ReplaceWorker(args, context);
	}
}
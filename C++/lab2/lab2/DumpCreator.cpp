#include "DumpCreator.h"

namespace MainWorkerCreators {

	Interfaces::Worker* DumpCreator::create_worker(std::vector<std::wstring>& args, MainContext::Context& context) const {
		return new MainWorkers::DumpWorker(args, context);
	}
}

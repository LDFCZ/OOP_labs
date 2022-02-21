#include "SortCreator.h"

namespace MainWorkerCreators {

	Interfaces::Worker* SortCreator::create_worker(std::vector<std::wstring>& args, MainContext::Context& context) const {
		return new MainWorkers::SortWorker(args, context);
	}
}
#include "ReaderCreator.h"

namespace MainWorkerCreators {

	Interfaces::Worker* ReaderCreator::create_worker(std::vector<std::wstring>& args, MainContext::Context& context) const {
		return new MainWorkers::ReaderWorker(args, context);
	}
}

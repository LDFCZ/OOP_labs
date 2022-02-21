#include "WriterCreator.h"

namespace MainWorkerCreators {

	Interfaces::Worker* WriterCreator::create_worker(std::vector<std::wstring>& args, MainContext::Context& context) const {
		return new MainWorkers::WriterWorker(args, context);
	}
}
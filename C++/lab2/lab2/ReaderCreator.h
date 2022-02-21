#pragma once

#include "Creator.h"
#include "ReaderWorker.h"

namespace MainWorkerCreators {

	class ReaderCreator : public Interfaces::Creator {
	public:
		Interfaces::Worker* create_worker(std::vector<std::wstring>&, MainContext::Context&) const override;
	};
}


#pragma once

#include "Creator.h"
#include "GrepWorker.h"

namespace MainWorkerCreators {

	class GrepCreator : public Interfaces::Creator {
	public:
		Interfaces::Worker* create_worker(std::vector<std::wstring>&, MainContext::Context&) const override;
	};
}


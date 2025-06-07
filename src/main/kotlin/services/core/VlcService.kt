package services.core

class VlcService {
    public fun open () {
        try {
            val process = ProcessBuilder("vlc").start()
        }
        catch (e: Exception) {
            println(e.message)
        }
    }

    public fun close() {
        ProcessHandle.allProcesses()
            .filter { it.info().command().orElse("").contains("vlc", ignoreCase = true) }
            .forEach { it.destroy() }
    }

    private fun isVlcRunning(): Boolean {
        return ProcessHandle.allProcesses()
            .anyMatch { it.info().command().orElse("").contains("vlc", ignoreCase = true) }
    }

}
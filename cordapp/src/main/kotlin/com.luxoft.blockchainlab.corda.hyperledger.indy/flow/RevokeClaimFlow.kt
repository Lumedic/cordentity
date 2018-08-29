package com.luxoft.blockchainlab.corda.hyperledger.indy.flow

import co.paralleluniverse.fibers.Suspendable
import net.corda.core.flows.*


object RevokeClaimFlow {
    @InitiatingFlow
    @StartableByRPC
    open class Issuer(
            private val revRegId: String,
            private val credRevId: String
    ) : FlowLogic<Unit>() {
        @Suspendable
        override fun call() {
            try {

                indyUser().revokeClaim(revRegId, credRevId)

            } catch(ex: Exception) {
                logger.error("", ex)
                throw FlowException(ex.message)
            }
        }
    }

    @InitiatedBy(Issuer::class)
    open class Prover(private val flowSession: FlowSession) : FlowLogic<Unit>() {
        override fun call() {
        }
    }
}
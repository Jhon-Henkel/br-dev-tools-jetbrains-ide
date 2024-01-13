package com.github.jhonhenkel.brdevtoolsjetbrainside.toolWindow

import com.github.jhonhenkel.brdevtoolsjetbrainside.services.RandomCnpjService
import com.intellij.openapi.components.service
import com.intellij.openapi.project.Project
import com.intellij.openapi.wm.ToolWindow
import com.intellij.openapi.wm.ToolWindowFactory
import com.intellij.ui.components.JBLabel
import com.intellij.ui.components.JBPanel
import com.intellij.ui.content.ContentFactory
import com.github.jhonhenkel.brdevtoolsjetbrainside.services.RandomCpfService
import javax.swing.JButton

class MyToolWindowFactory : ToolWindowFactory {

    override fun createToolWindowContent(project: Project, toolWindow: ToolWindow) {
        val myToolWindow = MyToolWindow(toolWindow)

//        val contentCpf = ContentFactory.getInstance().createContent(myToolWindow.getContentCpf(), null, false)
//        toolWindow.contentManager.addContent(contentCpf)

        val contentCnpj = ContentFactory.getInstance().createContent(myToolWindow.getContentCnpj(), null, false)
        toolWindow.contentManager.addContent(contentCnpj)
    }

    override fun shouldBeAvailable(project: Project) = true

    class MyToolWindow(toolWindow: ToolWindow) {

        private val cpfService = toolWindow.project.service<RandomCpfService>()
        private val cnpjService = toolWindow.project.service<RandomCnpjService>()


        fun getContentCpf() = JBPanel<JBPanel<*>>().apply {
            val label = JBLabel("")

            add(JButton("Random CPF").apply {
                addActionListener {
                    label.text = cpfService.generateRandomCpf()
                }
            })

            add(label)
        }

        fun getContentCnpj() = JBPanel<JBPanel<*>>().apply {
            val label = JBLabel("")

            add(JButton("Random CNPJ").apply {
                addActionListener {
                    label.text = cnpjService.generateRandomCnpj()
                }
            })

            add(label)
        }
    }
}

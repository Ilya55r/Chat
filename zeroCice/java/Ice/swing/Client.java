//
// Copyright (c) ZeroC, Inc. All rights reserved.
//

import com.zeroc.demos.Ice.swing.Demo.*;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.event.*;

public class Client extends JFrame
{
    static class Holder<T>
    {
        public Holder(T v)
        {
            value = v;
        }

        public T value;
    }

    public static void main(final String[] args)
    {
        SwingUtilities.invokeLater(() ->
        {
            try
            {
                //
                // Create and set up the window.
                //
                new Client(args);
            }
            catch(com.zeroc.Ice.LocalException e)
            {
                JOptionPane.showMessageDialog(null, e.toString(), "Initialization failed", JOptionPane.ERROR_MESSAGE);
            }
        });
    }

    Client(String[] args)
    {
        //
        // Initialize an Ice communicator.
        //
        try
        {
            com.zeroc.Ice.InitializationData initData = new com.zeroc.Ice.InitializationData();
            initData.properties = com.zeroc.Ice.Util.createProperties();
            initData.properties.load("config.client");
            initData.properties.setProperty("Ice.Default.Package", "com.zeroc.demos.Ice.swing");
            initData.dispatcher = (runnable, connection) -> SwingUtilities.invokeLater(runnable);

            _communicator = com.zeroc.Ice.Util.initialize(args, initData);
            Runtime.getRuntime().addShutdownHook(new Thread(() -> _communicator.destroy()));
        }
        catch(Throwable ex)
        {
            handleException(ex);
        }

        Container cp = this;

        JLabel l1 = new JLabel("Hostname");
        _hostname = new JTextField();
        JLabel l2 = new JLabel("Mode");
        _mode = new JComboBox<String>();
        JLabel l3 = new JLabel("Timeout");
        _timeoutSlider = new JSlider(0, MAX_TIME);
        _timeoutLabel = new JLabel("0.0");
        JLabel l4 = new JLabel("Delay");
        _delaySlider = new JSlider(0, MAX_TIME);
        _delayLabel = new JLabel("0.0");
        JPanel buttonPanel = new JPanel();
        _hello = new JButton("Hello World!");
        _shutdown = new JButton("Shutdown");
        _flush = new JButton("Flush");
        _flush.setEnabled(false);
        JSeparator statusPanelSeparator = new JSeparator();
        _status = new JLabel();
        _status.setText("Ready");

        //
        // Default to localhost.
        //
        _hostname.setText("127.0.0.1");
        _hostname.getDocument().addDocumentListener(new DocumentListener()
        {
            @Override
            public void changedUpdate(DocumentEvent e)
            {
                 updateProxy();
            }

            @Override
            public void insertUpdate(DocumentEvent e)
            {
                if(e.getDocument().getLength() > 0)
                {
                    _hello.setEnabled(true);
                    _shutdown.setEnabled(true);
                }
                updateProxy();
            }

            @Override
            public void removeUpdate(DocumentEvent e)
            {
                if(e.getDocument().getLength() == 0)
                {
                    _hello.setEnabled(false);
                    _shutdown.setEnabled(false);
                    _flush.setEnabled(false);
                }
                updateProxy();
            }
        });

        _mode.setModel(new DefaultComboBoxModel<String>(DELIVERY_MODE_DESC));

        _hello.addActionListener(e -> sayHello());
        _shutdown.addActionListener(e -> shutdown());
        _flush.addActionListener(e -> flush());
        _mode.addActionListener(e -> changeDeliveryMode(_mode.getSelectedIndex()));
        changeDeliveryMode(_mode.getSelectedIndex());
        _timeoutSlider.addChangeListener(new SliderListener(_timeoutSlider, _timeoutLabel));
        _timeoutSlider.addChangeListener(e -> updateProxy());
        _timeoutSlider.setValue(0);
        _delaySlider.addChangeListener(new SliderListener(_delaySlider, _delayLabel));
        _delaySlider.setValue(0);

        GridBagConstraints gridBagConstraints;

        cp.setMaximumSize(null);
        cp.setPreferredSize(null);
        cp.setLayout(new GridBagLayout());

        l1.setText("Hostname");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = GridBagConstraints.WEST;
        gridBagConstraints.insets = new Insets(5, 5, 5, 5);
        cp.add(l1, gridBagConstraints);

        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new Insets(5, 0, 5, 5);
        cp.add(_hostname, gridBagConstraints);

        l2.setText("Mode");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = GridBagConstraints.WEST;
        gridBagConstraints.insets = new Insets(0, 5, 5, 0);
        cp.add(l2, gridBagConstraints);

        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new Insets(0, 0, 5, 5);
        cp.add(_mode, gridBagConstraints);

        l3.setText("Timeout");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = GridBagConstraints.WEST;
        gridBagConstraints.insets = new Insets(0, 5, 5, 0);
        cp.add(l3, gridBagConstraints);

        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = GridBagConstraints.WEST;
        cp.add(_timeoutSlider, gridBagConstraints);

        _timeoutLabel.setMinimumSize(new Dimension(20, 17));
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = GridBagConstraints.WEST;
        gridBagConstraints.insets = new Insets(0, 5, 5, 5);
        cp.add(_timeoutLabel, gridBagConstraints);

        l4.setText("Delay");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = GridBagConstraints.WEST;
        gridBagConstraints.insets = new Insets(0, 5, 5, 0);
        cp.add(l4, gridBagConstraints);

        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = GridBagConstraints.WEST;
        cp.add(_delaySlider, gridBagConstraints);

        _delayLabel.setMinimumSize(new Dimension(20, 17));
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = GridBagConstraints.WEST;
        gridBagConstraints.insets = new Insets(0, 5, 5, 5);
        cp.add(_delayLabel, gridBagConstraints);

        _hello.setText("Hello World!");
        buttonPanel.add(_hello);

        _shutdown.setText("Shutdown");
        buttonPanel.add(_shutdown);

        _flush.setText("Flush");
        buttonPanel.add(_flush);

        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.ipady = 5;
        cp.add(buttonPanel, gridBagConstraints);

        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new Insets(0, 5, 5, 5);
        cp.add(statusPanelSeparator, gridBagConstraints);

        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new Insets(0, 5, 5, 5);
        cp.add(_status, gridBagConstraints);

        setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);

        addWindowListener(new WindowAdapter()
        {
            @Override
            public void windowClosing(WindowEvent e)
            {
                if(_communicator != null)
                {
                    _communicator.destroy();
                }
                dispose();
                Runtime.getRuntime().exit(0);
            }
        });

        setTitle("Ice - Hello World!");
        pack();
        locateOnScreen(this);
        setVisible(true);
    }

    // These two arrays match and match the order of the delivery mode enumeration.
    private final static DeliveryMode DELIVERY_MODES[] =
    {
        DeliveryMode.TWOWAY,
        DeliveryMode.TWOWAY_SECURE,
        DeliveryMode.ONEWAY,
        DeliveryMode.ONEWAY_BATCH,
        DeliveryMode.ONEWAY_SECURE,
        DeliveryMode.ONEWAY_SECURE_BATCH,
        DeliveryMode.DATAGRAM,
        DeliveryMode.DATAGRAM_BATCH,
    };

    private final static String DELIVERY_MODE_DESC[] = new String[]
    {
        "Twoway",
        "Twoway Secure",
        "Oneway",
        "Oneway Batch",
        "Oneway Secure",
        "Oneway Secure Batch",
        "Datagram",
        "Datagram Batch"
    };

    private enum DeliveryMode
    {
        TWOWAY,
        TWOWAY_SECURE,
        ONEWAY,
        ONEWAY_BATCH,
        ONEWAY_SECURE,
        ONEWAY_SECURE_BATCH,
        DATAGRAM,
        DATAGRAM_BATCH;

        HelloPrx apply(HelloPrx prx)
        {
            switch (this)
            {
                case TWOWAY:
                {
                    prx = prx.ice_twoway();
                    break;
                }
                case TWOWAY_SECURE:
                {
                    prx = prx.ice_twoway().ice_secure(true);
                    break;
                }
                case ONEWAY:
                {
                    prx = prx.ice_oneway();
                    break;
                }
                case ONEWAY_BATCH:
                {
                    prx = prx.ice_batchOneway();
                    break;
                }
                case ONEWAY_SECURE:
                {
                    prx = prx.ice_oneway().ice_secure(true);
                    break;
                }
                case ONEWAY_SECURE_BATCH:
                {
                    prx = prx.ice_batchOneway().ice_secure(true);
                    break;
                }
                case DATAGRAM:
                {
                    prx = prx.ice_datagram();
                    break;
                }
                case DATAGRAM_BATCH:
                {
                    prx = prx.ice_batchDatagram();
                    break;
                }
            }
            return prx;
        }

        public boolean isOneway()
        {
            return this == ONEWAY || this == ONEWAY_SECURE || this == DATAGRAM;
        }

        public boolean isBatch()
        {
            return this == ONEWAY_BATCH || this == DATAGRAM_BATCH || this == ONEWAY_SECURE_BATCH;
        }
    }

    private void updateProxy()
    {
        String host = _hostname.getText().toString().trim();
        if(host.length() == 0)
        {
            _status.setText("No hostname");
            return;
        }

        String s = "hello:tcp -h " + host + " -p 10000:ssl -h " + host + " -p 10001:udp -h " + host + " -p 10000";
        _helloPrx = HelloPrx.uncheckedCast(_communicator.stringToProxy(s));
        _helloPrx = _deliveryMode.apply(_helloPrx);
        int timeout = _timeoutSlider.getValue();
        if(timeout != 0)
        {
            _helloPrx = _helloPrx.ice_invocationTimeout(timeout);
        }

        //
        // The batch requests associated to the proxy are lost when we
        // update the proxy.
        //
        _flush.setEnabled(false);

        _status.setText("Ready");
    }

    private void sayHello()
    {
        if(_helloPrx == null)
        {
            return;
        }

        int delay = _delaySlider.getValue();
        try
        {
            if(!_deliveryMode.isBatch())
            {
                _status.setText("Sending request");
                final DeliveryMode mode = _deliveryMode;
                java.util.concurrent.CompletableFuture<Void> f = _helloPrx.sayHelloAsync(delay);
                final Holder<Boolean> response = new Holder<Boolean>(false);
                f.whenCompleteAsync((result, ex) ->
                {
                    if(ex == null)
                    {
                        assert (!response.value);
                        response.value = true;
                        _status.setText("Ready");
                    }
                    else
                    {
                        assert (!response.value);
                        response.value = true;
                        handleException(ex);
                    }
                },
                _helloPrx.ice_executor());
                com.zeroc.Ice.Util.getInvocationFuture(f).whenSent((ss, ex) ->
                {
                    if(ex == null)
                    {
                        if(mode.isOneway())
                        {
                            _status.setText("Ready");
                        }
                        else if(!response.value)
                        {
                            _status.setText("Waiting for response");
                        }
                    }
                });
            }
            else
            {
                _flush.setEnabled(true);
                _helloPrx.sayHello(delay);
                _status.setText("Queued sayHello request");
            }
        }
        catch(com.zeroc.Ice.LocalException ex)
        {
            handleException(ex);
        }
    }

    private void shutdown()
    {
        if(_helloPrx == null)
        {
            return;
        }

        try
        {
            if(!_deliveryMode.isBatch())
            {
                _status.setText("Sending request");
                final DeliveryMode mode = _deliveryMode;
                final Holder<Boolean> response = new Holder<Boolean>(false);
                java.util.concurrent.CompletableFuture<Void> f = _helloPrx.shutdownAsync();
                f.whenCompleteAsync((result, ex) ->
                {
                    if(ex == null)
                    {
                        response.value = true;
                        _status.setText("Ready");
                    }
                    else
                    {
                        response.value = true;
                        handleException(ex);
                    }
                },
                _helloPrx.ice_executor());
                com.zeroc.Ice.Util.getInvocationFuture(f).whenSent((ss, ex) ->
                {
                    if(ex == null)
                    {
                        if(mode.isOneway())
                        {
                            _status.setText("Ready");
                        }
                        else if(!response.value)
                        {
                            _status.setText("Waiting for response");
                        }
                    }
                });
            }
            else
            {
                _flush.setEnabled(true);
                _helloPrx.shutdown();
                _status.setText("Queued shutdown request");
            }
        }
        catch(com.zeroc.Ice.LocalException ex)
        {
            handleException(ex);
        }
    }

    private void flush()
    {
        if(_helloPrx == null)
        {
            return;
        }

        _helloPrx.ice_flushBatchRequestsAsync().whenCompleteAsync((result, ex) ->
            {
                if(ex != null)
                {
                    handleException(ex);
                }
            },
            _helloPrx.ice_executor());

        _flush.setEnabled(false);
        _status.setText("Flushed batch requests");
    }

    private void changeDeliveryMode(long id)
    {
        _deliveryMode = DELIVERY_MODES[(int)id];
        updateProxy();
    }

    private void handleException(final Throwable ex)
    {
        // Ignore CommunicatorDestroyedException which could occur on
        // shutdown.
        if(ex instanceof com.zeroc.Ice.CommunicatorDestroyedException)
        {
            return;
        }
        _status.setText(ex.getClass().getName());
    }

    private static class SliderListener implements ChangeListener
    {
        SliderListener(JSlider slider, JLabel label)
        {
            _slider = slider;
            _label = label;
        }

        @Override
        public void stateChanged(ChangeEvent ce)
        {
            float value = (float)(_slider.getValue() / 1000.0);
            _label.setText(String.format("%.1f", value));
        }

        private JSlider _slider;
        private JLabel _label;
    }

    private static void locateOnScreen(Component component)
    {
        Dimension paneSize = component.getSize();
        Dimension screenSize = component.getToolkit().getScreenSize();
        component.setLocation((screenSize.width - paneSize.width) / 2, (screenSize.height - paneSize.height) / 2);
    }

    private static final int MAX_TIME = 5000; // 5 seconds

    private JTextField _hostname;
    private JComboBox<String> _mode;
    private JSlider _timeoutSlider;
    private JLabel _timeoutLabel;
    private JSlider _delaySlider;
    private JLabel _delayLabel;
    private JButton _hello;
    private JButton _shutdown;
    private JButton _flush;
    private JLabel _status;

    private com.zeroc.Ice.Communicator _communicator;
    private DeliveryMode _deliveryMode;

    private HelloPrx _helloPrx = null;
}
